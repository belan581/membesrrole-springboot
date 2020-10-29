package com.abeltran.memberswebapp.service;

import java.util.List;

import com.abeltran.memberswebapp.model.Member;
import com.abeltran.memberswebapp.model.Role;


public interface MemberService{
	
	List<Member> findAllMembers();
	
	Member findById(int id);
	
	Member createMemberSubmit(Member member, List<Role> roles);
	
	void updateMemberSubmit(int id, Member member);
	
	Member findForLogin(String email, String password);
	
	void deleteMemberSubmit(int id);
	
}
