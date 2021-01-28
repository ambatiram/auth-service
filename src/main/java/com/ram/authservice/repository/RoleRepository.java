package com.ram.authservice.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ram.authservice.model.Role;
import com.ram.authservice.model.RoleName;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByRoleName(RoleName roleName);
	Boolean existsByRoleName(String name);
	Role findByRoleName(String roleName);
}
