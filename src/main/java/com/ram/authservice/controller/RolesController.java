package com.ram.authservice.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ram.authservice.dto.ApiResponse;
import com.ram.authservice.dto.PermissionRequest;
import com.ram.authservice.dto.RoleRequest;
import com.ram.authservice.model.Permission;
import com.ram.authservice.model.Role;
import com.ram.authservice.repository.PermissionRepository;
import com.ram.authservice.repository.RoleRepository;

@RestController
@RequestMapping("/api")
public class RolesController {
	
	@Autowired
	private RoleRepository rolesRepository;
	
	@Autowired
	private PermissionRepository permissionsRepository;
	
	@GetMapping("/roles")
	public ResponseEntity<?> getAllRoles(){
		List<Role> roles = rolesRepository.findAll();
		
		return  new ResponseEntity<Object>(roles,
                HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/roles")
	public ResponseEntity<?> postRole(@Valid @RequestBody RoleRequest roleRequest){
		if(rolesRepository.existsByRoleName(roleRequest.getRoleName())) {
            return new ResponseEntity(new ApiResponse(false, "Role is already exists"),
                    HttpStatus.BAD_REQUEST);
        }

		Role role = new Role();
		role.setRoleName(roleRequest.getRoleName());
	
		
		for(PermissionRequest permissionReq : roleRequest.getPermissions()) {
			Permission permission = permissionsRepository.findByPermissionCode(permissionReq.getPermissionName());
			permission.getRoles().add(role);
			role.getPermissions().add(permission);
		}
		
		Role result = rolesRepository.save(role);
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/role/{id}")
                .buildAndExpand(result.getId()).toUri();
		
		return ResponseEntity.created(location).body(new ApiResponse(true, "Role is created successfully"));
	}

	
	

}
