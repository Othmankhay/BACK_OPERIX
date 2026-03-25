package com.stellantis.operix.controller;

import com.stellantis.operix.dto.auth.LoginRequest;
import com.stellantis.operix.dto.auth.LoginResponse;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest req) {
        AuthController authService = null;
        return ResponseEntity.ok(authService.login(req).getBody());
    }

    @PostMapping("/refresh")
    public <RefreshTokenRequest> ResponseEntity<LoginResponse> refresh(
            @RequestBody RefreshTokenRequest req) {
        Object authService = null;
        return ResponseEntity.ok(
                authService.refreshToken(req.getToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestBody RefreshTokenRequest req) {
        authService.logout(req.getToken());
        return ResponseEntity.noContent().build();
    }
}
