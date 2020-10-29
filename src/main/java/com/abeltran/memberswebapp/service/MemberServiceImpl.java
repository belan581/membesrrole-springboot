package com.abeltran.memberswebapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.abeltran.memberswebapp.model.Member;
import com.abeltran.memberswebapp.model.Role;
import com.abeltran.memberswebapp.repository.MemberRepository;
import com.abeltran.memberswebapp.repository.RoleRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Member> findAllMembers(){
		List<Member> members = memberRepository.findAll();
		return members;
	}

	public Member createMemberSubmit(Member memberForm, List<Role> roles) {
		if(memberForm.getCreatedDate() == null) {
			Date date = new Date();	
			memberForm.setCreatedDate(date);
		}
		Member newMember = new Member();
		newMember.setName(memberForm.getName());
		newMember.setEmail(memberForm.getEmail());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        newMember.setPassword(encoder.encode(memberForm.getPassword()));
        newMember.setRole(roles);
        newMember.setCreatedDate(memberForm.getCreatedDate());
		memberRepository.saveAndFlush(newMember);
		
		return newMember;
	}

	
	public Member findForLogin(String email, String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
        Member member = memberRepository.findByNameAndEmail(email, password);
		return member;
	}

	public void updateMemberSubmit(int id, Member member) {
		Member currentmember = memberRepository.findById(id);
		List<Role> roles = roleRepository.findAll();
		currentmember.setName(member.getName());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		currentmember.setPassword(encoder.encode(member.getPassword()));
		currentmember.setRole(roles);
	    memberRepository.saveAndFlush(currentmember);
	}

	public Member findById(int id) {
		Member member = memberRepository.findById(id);
		return member;
	}

	public void deleteMemberSubmit(int id) {
		Member member = memberRepository.findById(id);
	    memberRepository.delete(member);
		
	}


}
