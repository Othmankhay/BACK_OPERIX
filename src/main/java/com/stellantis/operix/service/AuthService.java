package com.stellantis.operix.service;

import com.stellantis.operix.dto.auth.LoginRequest;
import com.stellantis.operix.dto.auth.LoginResponse;
import com.stellantis.operix.entity.RefreshToken;
import com.stellantis.operix.entity.Utilisateur;
import com.stellantis.operix.exception.JwtAuthException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    public LoginResponse login(LoginRequest req) {
        Utilisateur user = utilisateurRepo
                .findByEmail(req.getEmail()).orElseThrow();
        if (!passwordEncoder.matches(
                req.getPassword(), user.getMotDePasse()))
            throw new BadCredentialsException("Mdp incorrect");
        String access  = jwtProvider.generateAccessToken(user);
        String refresh = jwtProvider.generateRefreshToken(user);
        refreshTokenRepo.save(new RefreshToken(
                refresh, user, now().plusDays(7)));
        return new LoginResponse(access, refresh);
    }

    public LoginResponse refreshToken(String token) {
        RefreshToken rt = refreshTokenRepo
                .findByToken(token).orElseThrow();
        if (rt.isRevoque() || rt.getExpireLe().isBefore(now()))
            throw new JwtAuthException("Token invalide");
        String newAccess =
                jwtProvider.generateAccessToken(rt.getUtilisateur());
        return new LoginResponse(newAccess, token);
    }

    public void logout(String token) {
        refreshTokenRepo.findByToken(token)
                .ifPresent(rt -> { rt.setRevoque(true);
                    refreshTokenRepo.save(rt); });
    }
}