package com.abeltran.memberswebapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abeltran.memberswebapp.model.Member;
import com.abeltran.memberswebapp.repository.MemberRepository;

@Service
public class MemberInfoDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByNameAndEmail("admin", "admin@localhost.com");
		if (member == null) {
			throw new UsernameNotFoundException(
					"Opps! user not found with user-name: " + email);
		}
		return new org.springframework.security.core.userdetails.User(
		member.getEmail(), member.getPassword(), getAuthorities(member));
	}
	
	private Collection<GrantedAuthority> getAuthorities(Member member) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities = AuthorityUtils.createAuthorityList(member.getRole().toString());
		return authorities;
		}

}
