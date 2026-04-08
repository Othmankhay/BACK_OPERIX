package com.stellantis.operix.controller;

import com.stellantis.operix.dto.auth.LoginRequest;
import com.stellantis.operix.dto.auth.LoginResponse;
import com.stellantis.operix.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

@RequestMapping("api/auth")

@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(
            @RequestBody RefreshTokenRequest req) {
        return ResponseEntity.ok(
                authService.refreshToken(req.token()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestBody RefreshTokenRequest req) {
        authService.logout(req.token());
        return ResponseEntity.noContent().build();
    }

    public record RefreshTokenRequest(String token) {
    }
}
