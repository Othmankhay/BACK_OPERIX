package com.stellantis.operix.entity;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;


@Table( name = "sous_projet")

public class SousProjet {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id ;
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;
    @Column(unique = true )
    private String code,nom;
    private boolean actif = true;

}
