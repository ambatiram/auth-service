package com.ram.authservice.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ram.authservice.model.Role;
import com.ram.authservice.model.User;

@Repository
@Transactional
public class UserRoleRepositoryImpl implements UserRoleRepository{

	@Autowired
    EntityManagerFactory emf;

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRolesByUserId(Long userId) {
		
		EntityManager em = emf.createEntityManager();
        Query query = em.createNativeQuery("select r.id, r.role_name from user_role ur\r\n" + 
        		"join user u on u.id = ur.user_id\r\n" + 
        		"join role r on r.id = ur.role_id\r\n" + 
        		"where user_id = "+userId);	

		@SuppressWarnings("unchecked")
		List<Role> roles = new ArrayList<Role>();
		List<Object[]> results = query.getResultList();
		for(Object[] result: results){
			Role role = new Role();
			role.setId((BigInteger)result[0]);
			role.setRoleName((String) result[1]);
			roles.add(role);
		}
	    em.close();
	
	    return roles;
	}
}
