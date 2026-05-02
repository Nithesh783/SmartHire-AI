package com.smarthire.service;

import com.smarthire.dto.AuthResponse;
import com.smarthire.dto.LoginRequest;
import com.smarthire.dto.RegisterRequest;

public interface AuthService {

    AuthResponse registerUser(RegisterRequest request);

    AuthResponse loginUser(LoginRequest request);
}