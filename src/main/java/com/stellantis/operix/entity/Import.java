package com.stellantis.operix.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
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
    @Lob
    private String logDetails;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    private LocalDateTime dateImport;
}