package com.abeltran.memberswebapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abeltran.memberswebapp.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
	
	Member findById(int id);
	
	List<Member> findByName(String name);
	
	Member findByEmail(String email);
	
	Member findByNameAndEmail(String email, String password);
	
	boolean existsMemberByName(String name);
	
}