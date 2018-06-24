package com.can.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	// loginPage("/showMyLoginPage")
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		return "login";
	}
	
	// .exceptionHandling().accessDeniedPage("/access-denied")
	@GetMapping("/access-denied")
	public String showAccessDeniedPage() {
		return "access-denied";
	}
	
}
