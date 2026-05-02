package com.smarthire.dto;

public class AuthResponse {

    private String message;
    private boolean status;
    private String token;

    public AuthResponse() {
    }

    public AuthResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public AuthResponse(String message, boolean status, String token) {
        this.message = message;
        this.status = status;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}