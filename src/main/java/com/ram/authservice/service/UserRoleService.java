package com.ram.authservice.service;

import java.util.List;

import com.ram.authservice.model.Role;
import com.ram.authservice.model.User;

public interface UserRoleService {
	
	List<Role> getUserRolesByUserId(Long userId);
	List<Integer> getUserPermissionsByUserId(Long userId);

}
