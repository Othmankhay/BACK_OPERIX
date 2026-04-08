package com.stellantis.operix.entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "projets")
@Getter
@Setter
public class Projet {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String code, nom;
    private String description;
    private Boolean actif = true;
    private LocalDateTime createdAt;
}
