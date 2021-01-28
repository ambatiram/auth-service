package com.ram.authservice.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.authservice.dto.ApiResponse;
import com.ram.authservice.exceptions.ResourceNotFoundException;
import com.ram.authservice.model.Role;
import com.ram.authservice.model.User;
import com.ram.authservice.repository.RoleRepository;
import com.ram.authservice.repository.UserRepository;
import com.ram.authservice.service.UserRoleService;

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
}
