package com.stellantis.operix.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String email;
    private String role;

    public LoginResponse(String newAccess, String token) {
    }
}