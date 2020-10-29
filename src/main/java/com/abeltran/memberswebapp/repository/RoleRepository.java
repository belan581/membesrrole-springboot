package com.abeltran.memberswebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abeltran.memberswebapp.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String string);
	
	boolean existsRoleByName(String name);
	
}
