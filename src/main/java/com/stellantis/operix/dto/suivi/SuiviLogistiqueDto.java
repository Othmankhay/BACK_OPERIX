package com.stellantis.operix.dto.suivi;

import com.stellantis.operix.enums.StatutLogistique;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class SuiviLogistiqueDto {
    private Integer id;
    private String reference, designation;
    private String nomProjet, nomSousProjet;
    private String codeFournisseur, nomFournisseur;
    private String utilisateurPSA, magasin, site;
    private StatutLogistique statut;
    private Integer quantiteEcheancee, quantiteLivree;
    private Integer quantiteReliquat;    // NEW
    private LocalDate dateEcheance, dateLivraisonConfirmee;
    private Boolean escaladeProjet;      // NEW
    private String statutQualite;        // NEW
    private String motifRefusQualite;    // NEW
    private String statutBl;             // NEW
    private String numeroBl;             // NEW (SAP)
    private String numeroTracking;       // NEW (SAP)
    private String dernierCommentaire, dernierPplRlog;
}