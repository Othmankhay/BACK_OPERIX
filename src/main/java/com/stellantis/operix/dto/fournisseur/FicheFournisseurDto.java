package com.stellantis.operix.dto.fournisseur;

import com.stellantis.operix.dto.suivi.SuiviLogistiqueDto;
import com.stellantis.operix.enums.StatutLogistique;

import java.util.List;
import java.util.Map;

public class FicheFournisseurDto {
    private String code, nom;
    private Integer totalPieces;
    private Integer totalQteEcheancee, totalQteLivree;
    private Double tauxLivraison;
    private Map<StatutLogistique, Long> repartitionStatuts;
    private List<SuiviLogistiqueDto> pieces;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getTotalPieces() {
        return totalPieces;
    }

    public void setTotalPieces(Integer totalPieces) {
        this.totalPieces = totalPieces;
    }

    public Double getTauxLivraison() {
        return tauxLivraison;
    }

    public void setTauxLivraison(Double tauxLivraison) {
        this.tauxLivraison = tauxLivraison;
    }

    public Map<StatutLogistique, Long> getRepartitionStatuts() {
        return repartitionStatuts;
    }

    public void setRepartitionStatuts(Map<StatutLogistique, Long> repartitionStatuts) {
        this.repartitionStatuts = repartitionStatuts;
    }
}
