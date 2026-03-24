package com.stellantis.operix.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "imports")
@Getter
@Setter
public class Import {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String typeSource;  // SAP|Excel|CSV
    private String fichierNom, statut;
    private Integer lignesImportees, lignesErreur;
    private String description;
    @Column(columnDefinition = "jsonb")
    private String logDetails;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    private LocalDateTime dateImport;
}