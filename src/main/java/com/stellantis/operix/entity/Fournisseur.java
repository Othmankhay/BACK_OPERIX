package com.stellantis.operix.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fournisseurs")
@Getter
@Setter
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)

    private String code,nom;
    private String pays, contactNom , contactEmail, contactTel;
    private boolean actif = true;
}
