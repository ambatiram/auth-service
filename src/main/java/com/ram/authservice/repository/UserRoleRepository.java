package com.ram.authservice.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ram.authservice.model.Role;
import com.ram.authservice.model.User;


public interface UserRoleRepository {
	
	
	List<Role> getRolesByUserId(Long userId);

	List<Integer> getUserPermissionsByUserId(Long userId);
}
