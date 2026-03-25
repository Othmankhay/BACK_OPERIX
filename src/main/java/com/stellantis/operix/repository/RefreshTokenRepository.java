package com.stellantis.operix.repository;

import com.stellantis.operix.entity.RefreshToken;
import com.stellantis.operix.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUtilisateur(Utilisateur u);
}