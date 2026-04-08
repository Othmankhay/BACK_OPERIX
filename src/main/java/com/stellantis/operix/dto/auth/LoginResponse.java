package com.stellantis.operix.dto.auth;

public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String email;
    private String role;

    public LoginResponse(String accessToken, String refreshToken, String email, String role) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.email = email;
        this.role = role;
    }

    public LoginResponse(String newAccess, String token) {
        this.accessToken = newAccess;
        this.refreshToken = token;
        this.email = null;
        this.role = null;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}