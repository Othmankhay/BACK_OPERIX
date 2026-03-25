package com.stellantis.operix.entity;

import com.stellantis.operix.enums.StatutLogistique;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "suivi_logistique")
@Getter
@Setter

public class SuiviLogistique {
    @Id
    @GeneratedValue(strategy = IDENTITY)

    private Integer Id;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Enumerated(EnumType.STRING)
    private StatutLogistique statut;


    public Object getQuantiteEcheancee() {
    }

    public Object getQuantiteLivree() {
    }

    public Object getDateEcheance() {
    }

    public Object isFauxManquant() {
    }

    public Object isEscaladeProjet() {
    }

    public Object getStatutQualite() {
    }

    public Object getStatutBl() {
    }
}
