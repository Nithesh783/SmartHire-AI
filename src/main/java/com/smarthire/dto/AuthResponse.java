package com.smarthire.dto;

public class AuthResponse {

	private String message;
	private boolean success;
	private String token;
	private String role;

	public AuthResponse() {
	}

	public AuthResponse(String message, boolean success) {
		this.message = message;
		this.success = success;
	}

	public AuthResponse(String message, boolean success, String token, String role) {
		this.message = message;
		this.success = success;
		this.token = token;
		this.role = role;
	}

	public String getMessage() {
		return message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getToken() {
		return token;
	}

	public String getRole() {
		return role;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setRole(String role) {
		this.role = role;
	}
}