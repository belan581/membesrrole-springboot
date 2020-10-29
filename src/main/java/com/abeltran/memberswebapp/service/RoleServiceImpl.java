package com.abeltran.memberswebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abeltran.memberswebapp.model.Role;
import com.abeltran.memberswebapp.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	
	public void createRole(Role role) {
		
		roleRepository.save(role);
	}

	public List<Role> listAllroles() {
		List<Role> roles = roleRepository.findAll();
		return roles;
	}

}
