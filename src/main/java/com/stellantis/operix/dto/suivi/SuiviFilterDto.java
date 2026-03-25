package com.stellantis.operix.dto.suivi;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SuiviFilterDto {
    private String projet;
    private String sousProjet;
    private String statut;
    private String fournisseur;
    private String search;
    private LocalDateTime since;
}