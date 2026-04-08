package com.stellantis.operix.entity;


import com.stellantis.operix.enums.StatutLogistique;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "historique_statuts")
@Getter
@Setter
public class HistoriqueStatut {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @ManyToOne @JoinColumn(name = "article_id")
    private Article article;
    @Enumerated(EnumType.STRING)
    private StatutLogistique ancienStatut, nouveauStatut;
    @ManyToOne @JoinColumn(name = "modifie_par_id")
    private Utilisateur modifiePar;
    private String sourceChangement; // manuel|import_sap|systeme
    private String commentaire;
    private LocalDateTime changedAt;
}