package com.ram.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ram.authservice.model.Role;
import com.ram.authservice.model.User;
import com.ram.authservice.repository.UserRoleRepository;

@Service
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	private UserRoleRepository userRoleRepo;
	


	@Override
	public List<Role> getUserRolesByUserId(Long userId) {
		return userRoleRepo.getRolesByUserId(userId);
	}

}
