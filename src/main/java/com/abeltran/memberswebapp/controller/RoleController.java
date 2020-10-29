package com.abeltran.memberswebapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abeltran.memberswebapp.model.Role;
import com.abeltran.memberswebapp.repository.RoleRepository;

@Controller
@RequestMapping("/roles")
public class RoleController {
	
	private RoleRepository roleRepository;
	
	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	@GetMapping("")
	public String home(Model model) {
		return "roles/home";
	}
	
	@GetMapping("/list")
	public String allRoles(Model model) {
		List<Role> roles = roleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("roles", roles);
		return "roles/list";
	}
	
	@GetMapping("/create")
	public String createMemberForm(Model model) {
		model.addAttribute("role", new Role());
	    return "roles/create";
	}
	
	@PostMapping("/create")
	public String createRoleSubmit(@ModelAttribute Role role, Model model) {
		roleRepository.save(role);
	    model.addAttribute("role", role);
	    return "roles/result";
	}
	
	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
	    Role role = roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + id));
	    model.addAttribute("role", role);
	    return "roles/update";
	}
	
	@PostMapping("/update/{id}")
	public String updateMember(@PathVariable("id") Integer id, @Valid Role role, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        role.setId(id);
	        return "roles/update";
	    }
	    roleRepository.save(role);
	    model.addAttribute("role", roleRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
	    return "redirect:/roles/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteMember(@PathVariable("id") Integer id, Model model) {
	    Role role = roleRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid member Id:" + id));
	    roleRepository.delete(role);
	    model.addAttribute("roles", roleRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
	    return "redirect:/roles/list";
	}

}
