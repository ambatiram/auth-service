package com.ram.authservice.repository;

import java.util.List;

import com.ram.authservice.model.Role;

public interface UserRoleRepository {
	
	
	List<Role> getRolesByUserId(Long userId);

	List<Integer> getUserPermissionsByUserId(Long userId);
}
