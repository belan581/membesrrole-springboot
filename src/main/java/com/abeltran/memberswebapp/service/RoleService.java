package com.abeltran.memberswebapp.service;


import java.util.List;

import com.abeltran.memberswebapp.model.Role;

public interface RoleService {
	
	List<Role> listAllroles();
	
	void createRole(Role role);

}
