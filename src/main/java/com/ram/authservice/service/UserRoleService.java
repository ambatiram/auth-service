package com.ram.authservice.service;

import java.util.List;

import com.ram.authservice.model.Role;

public interface UserRoleService {
	
	List<Role> getUserRolesByUserId(Long userId);
	List<Integer> getUserPermissionsByUserId(Long userId);

}
