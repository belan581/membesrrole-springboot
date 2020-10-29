package com.abeltran.memberswebapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abeltran.memberswebapp.model.Member;
import com.abeltran.memberswebapp.model.Role;
import com.abeltran.memberswebapp.service.MemberService;
import com.abeltran.memberswebapp.service.RoleService;


@Controller
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("")
	public String home(Model model) {
		return "members/home";
	}
	
	@GetMapping("/list")
	public String allMembers(Model model) {
		List<Member> members = memberService.findAllMembers();
		model.addAttribute("members", members);
		return "members/list";
	}
	
	@GetMapping("/create")
	public String createMemberForm(Model model) {
		List<Role> roles = roleService.listAllroles();
		model.addAttribute("roles", roles);
		model.addAttribute("member", new Member());
	    return "members/create";
	}
	
	@PostMapping("/create")
	public String createMemberSubmit(@ModelAttribute Member member, @RequestParam List<Role> roles, Model model) {
		memberService.createMemberSubmit(member, roles);
	    model.addAttribute("member", member);
	    return "members/result";
	}
	
	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Member member = memberService.findById(id);
	    List<Role> roles = roleService.listAllroles();
		model.addAttribute("roles", roles);
	    model.addAttribute("member", member);
	    return "members/update";
	}
	
	@PostMapping("/update/{id}")
	public String updateMember(@PathVariable("id") Integer id, @Valid Member member,@RequestParam List<Role> roles, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        member.setId(id);
	        return "members/update";
	    }
	    memberService.updateMemberSubmit(id, member);
	    model.addAttribute("member", memberService.findAllMembers());
	    return "redirect:/members/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteMember(@PathVariable("id") Integer id, Model model) {
	    memberService.deleteMemberSubmit(id);
	    model.addAttribute("members", memberService.findAllMembers());
	    return "redirect:/members/list";
	}

}
