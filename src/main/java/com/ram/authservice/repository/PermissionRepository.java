package com.ram.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ram.authservice.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{
	Permission findByPermissionCode(String code);
	Boolean existsByPermissionCode(String code);
	
}
