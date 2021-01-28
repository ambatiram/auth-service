package com.ram.authservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.ram.authservice.model.Permission;
import com.ram.authservice.repository.PermissionRepository;

@RestController
@RequestMapping("/api")
public class PermissionController {

	@Autowired
	private PermissionRepository permissionsRepository;
	
	@GetMapping("/permissions")
	public ResponseEntity<?> getAllPermissions(){
		List<Permission> permissions = permissionsRepository.findAll();
		
		return  new ResponseEntity<Object>(permissions,
                HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/permissions")
	public ResponseEntity<?> postPermission(@Valid @RequestBody PermissionRequest permissionRequest){
		if(permissionsRepository.existsByPermissionCode(permissionRequest.getPermissionName())) {
            return new ResponseEntity(new ApiResponse(false, "Permission is already exists"),
                    HttpStatus.BAD_REQUEST);
        }
		
		// Creating Permission
		Permission permission = new Permission(permissionRequest.getPermissionName());
		
		Permission result = permissionsRepository.save(permission);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/permissions/{id}")
                .buildAndExpand(result.getId()).toUri();
		
		return ResponseEntity.created(location).body(new ApiResponse(true, "Permission created successfully"));
	}
}
