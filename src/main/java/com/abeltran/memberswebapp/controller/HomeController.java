package com.abeltran.memberswebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@GetMapping("/login")
	public String showLoginForm(){
	    return "login";
	}
	
	@GetMapping("")
	public String home(){
	    return "index";
	}

}
