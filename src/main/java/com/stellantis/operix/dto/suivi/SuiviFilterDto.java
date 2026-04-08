package com.stellantis.operix.dto.suivi;

import java.time.LocalDateTime;

public class SuiviFilterDto {
    private String projet;
    private String sousProjet;
    private String statut;
    private String fournisseur;
    private String search;
    private LocalDateTime since;

    public String getProjet() {
        return projet;
    }

    public String getStatut() {
        return statut;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public String getSearch() {
        return search;
    }
}