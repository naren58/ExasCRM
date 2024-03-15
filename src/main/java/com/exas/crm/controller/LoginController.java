package com.exas.crm.controller;


import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exas.crm.model.User;
import com.exas.crm.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final UserService userServiceImpl;
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/success")
	@ResponseBody
	public String successMessage() {
		return "Login Success";
	}
	
	@GetMapping("/profile")
	@ResponseBody
	public User loadAuthenticatedUserInfo(@AuthenticationPrincipal OAuth2User userPrincipal){
		return userServiceImpl.loadUserByEmail(userPrincipal.getAttributes().get("email").toString());
	}
	
	@GetMapping("/profiles")
	@ResponseBody
	public List<User> loadAllAuthenticatedUserData(){
		return userServiceImpl.loadAllAuthenticatedUserData();
	}	
}
