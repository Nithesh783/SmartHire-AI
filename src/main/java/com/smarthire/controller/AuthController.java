package com.smarthire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smarthire.dto.AuthResponse;
import com.smarthire.dto.LoginRequest;
import com.smarthire.dto.RegisterRequest;
import com.smarthire.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public AuthResponse registerUser(@RequestBody RegisterRequest request) {
		return authService.registerUser(request);
	}

	@PostMapping("/login")
	public AuthResponse loginUser(@RequestBody LoginRequest request) {
		return authService.loginUser(request);
	}
}