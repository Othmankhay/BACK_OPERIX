package com.stellantis.operix.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name ="utilisateurs")

@Getter
@Setter
@NoArgsConstructor


public abstract class Utilisateur implements UserDetails {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String nom, prenom;
    @Column(unique = true)
    private String email;
    private String motDePasse;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
    private Boolean actif = true;


    }

