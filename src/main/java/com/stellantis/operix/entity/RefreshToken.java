package com.stellantis.operix.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
public class RefreshToken {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String token;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    private LocalDateTime expireLe;
    private Boolean revoque = false;
}