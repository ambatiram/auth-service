package com.ram.authservice.controller;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ram.authservice.dto.ApiResponse;
import com.ram.authservice.exceptions.ResourceNotFoundException;
import com.ram.authservice.model.Role;
import com.ram.authservice.model.User;
import com.ram.authservice.repository.RoleRepository;
import com.ram.authservice.repository.UserRepository;
import com.ram.authservice.service.UserRoleService;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
@RequestMapping("/api")
public class UserRolePermController {
	
	@Autowired
	private RoleRepository rolesRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleService userRoleService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/users/{userId}/roles")
	public ResponseEntity<?> getRolesForUser(@PathVariable Long userId){
		Optional<User> user = userRepository.findById(userId);
		if(!user.isPresent()) {
			return new ResponseEntity(new ResourceNotFoundException("User", "id", userId),
                    HttpStatus.BAD_REQUEST);
		}
		
		List<Role> roles = userRoleService.getUserRolesByUserId(userId);
		
		
		
		return new ResponseEntity<>(roles , HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/users/{userId}/roles")
	public ResponseEntity<?> postRolesForUser(@PathVariable Long userId, @RequestBody List<String> roles){
		
		Optional<User> user = userRepository.findById(userId);
		if(!user.isPresent()) {
			return new ResponseEntity(new ResourceNotFoundException("User", "id", userId),
                    HttpStatus.BAD_REQUEST);
		}
		
		User optionalUser = user.get();
		
		for(String role: roles) {
			Role roleToAdd = rolesRepository.findByRoleName(role);
			optionalUser.getRoles().add(roleToAdd);
		}
		
		userRepository.save(optionalUser);
		
		return new ResponseEntity(new ApiResponse(true, "User Roles are created successfully"), HttpStatus.CREATED);
		
	}
	
	@PostMapping("/users/{userId}/permissions")
	public ResponseEntity<?> postPermissionsEligibilityCheck(@PathVariable Long userId, @RequestBody List<Integer> permissionIds){
		List<Integer> userPermissions = userRoleService.getUserPermissionsByUserId(userId);
        Collection<Integer> combined = new HashSet<Integer>();
        combined.addAll(permissionIds);
        combined.addAll(userPermissions);
        
        combined.removeAll( userPermissions );
        
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("allowed", userPermissions);
        results.put("notAllowed", combined);


		return new ResponseEntity(results, HttpStatus.OK);
	}
	
}
