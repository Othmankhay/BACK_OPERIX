package com.stellantis.operix.service;

import com.stellantis.operix.dto.auth.LoginRequest;
import com.stellantis.operix.dto.auth.LoginResponse;
import com.stellantis.operix.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepo;

    public LoginResponse login(LoginRequest req) {
        return new LoginResponse("access-token", "refresh-token");
    }

    public LoginResponse refreshToken(String token) {
        String safeToken = token == null ? "" : token;
        return new LoginResponse("access-token", safeToken);
    }

    public void logout(String token) {
        if (token == null || token.isBlank()) {
            return;
        }
        refreshTokenRepo.findByToken(token).ifPresent(refreshTokenRepo::delete);
    }
}