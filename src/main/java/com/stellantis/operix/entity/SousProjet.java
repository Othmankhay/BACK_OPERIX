package com.stellantis.operix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
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
