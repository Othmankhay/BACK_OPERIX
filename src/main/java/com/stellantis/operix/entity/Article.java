package com.stellantis.operix.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name="article")
@Getter
@Setter
@NoArgsConstructor


public class Article {
    @Id
    @GeneratedValue(strategy = IDENTITY)

    private Integer id;
    @Column(unique = true)
    private String references,referenceSap,designation;
    private String domaine, serie, ru, affaire,magasin;
    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;
    @ManyToOne
    @JoinColumn(name = "sous_projet_id")
    private SousProjet sousProjet;
    @ManyToOne
    @JoinColumn(name ="utilisateur_id")
    private Utilisateur utilisateur;







}

