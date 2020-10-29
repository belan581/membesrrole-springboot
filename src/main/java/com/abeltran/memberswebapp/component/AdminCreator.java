package com.abeltran.memberswebapp.component;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abeltran.memberswebapp.model.Member;
import com.abeltran.memberswebapp.model.Role;
import com.abeltran.memberswebapp.repository.MemberRepository;
import com.abeltran.memberswebapp.repository.RoleRepository;
import com.abeltran.memberswebapp.service.MemberService;

@Component
public class AdminCreator {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostConstruct
	public void adminCreate() {
		if(!roleRepository.existsRoleByName("ADMIN"))
		{
			Role role = new Role();
			role.setName("ADMIN");
			roleRepository.save(role);
		}
		List<Role> roles = roleRepository.findAll();
		if(!memberRepository.existsMemberByName("admin")) {
			Member member = new Member("admin@localhost.com","admin","admin");
			memberService.createMemberSubmit(member, roles);
		}
		
	}

}
