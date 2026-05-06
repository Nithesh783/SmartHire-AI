package com.smarthire.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smarthire.dto.AuthResponse;
import com.smarthire.dto.LoginRequest;
import com.smarthire.dto.RegisterRequest;
import com.smarthire.entity.User;
import com.smarthire.repository.UserRepository;
import com.smarthire.security.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public AuthResponse registerUser(RegisterRequest request) {

		if (userRepository.existsByEmail(request.getEmail())) {
			return new AuthResponse("Email already registered", false);
		}

		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(request.getRole());

		userRepository.save(user);

		return new AuthResponse("User registered successfully", true);
	}

	@Override
	public AuthResponse loginUser(LoginRequest request) {

		Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

		if (optionalUser.isEmpty()) {
			return new AuthResponse("User not found", false);
		}

		User user = optionalUser.get();

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			return new AuthResponse("Invalid password", false);
		}

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		// ✅ Generate token with role
		String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

		// ✅ IMPORTANT FIX: return role also
		return new AuthResponse("Login successful", true, token, user.getRole().name() // ✅ THIS FIXES YOUR ISSUE
		);
	}
}