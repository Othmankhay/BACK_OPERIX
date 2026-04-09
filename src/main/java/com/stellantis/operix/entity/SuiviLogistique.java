package com.stellantis.operix.entity;

import com.stellantis.operix.enums.StatutLogistique;
import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.persistence.Id;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "suivi_logistique")
public class SuiviLogistique {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Enumerated(EnumType.STRING)
    private StatutLogistique statut;
    private Integer quantiteEcheancee;
    private Integer quantiteLivree;
    private Integer quantiteReliquat;
    private LocalDate dateEcheance;
    private LocalDate dateLivraisonConfirmee;
    private boolean fauxManquant;
    private boolean escaladeProjet;
    private String statutQualite;
    private String motifRefusQualite;
    private String statutBl;
    private String numeroBl;
    private String numeroTracking;


    public Integer getId() {
        return id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public StatutLogistique getStatut() {
        return statut;
    }

    public void setStatut(StatutLogistique statut) {
        this.statut = statut;
    }

    public Integer getQuantiteEcheancee() {
        return quantiteEcheancee;
    }

    public void setQuantiteEcheancee(Integer quantiteEcheancee) {
        this.quantiteEcheancee = quantiteEcheancee;
    }

    public Integer getQuantiteLivree() {
        return quantiteLivree;
    }

    public Integer getQuantiteReliquat() {
        return quantiteReliquat;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public Boolean getEscaladeProjet() {
        return escaladeProjet;
    }

    public void setEscaladeProjet(boolean escaladeProjet) {
        this.escaladeProjet = escaladeProjet;
    }

    public String getStatutQualite() {
        return statutQualite;
    }

    public void setStatutQualite(String statutQualite) {
        this.statutQualite = statutQualite;
    }

    public void setMotifRefusQualite(String motifRefusQualite) {
        this.motifRefusQualite = motifRefusQualite;
    }

    public String getStatutBl() {
        return statutBl;
    }
}
