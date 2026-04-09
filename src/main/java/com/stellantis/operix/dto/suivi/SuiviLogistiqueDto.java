package com.stellantis.operix.dto.suivi;

import com.stellantis.operix.enums.StatutLogistique;
import java.time.LocalDate;

@SuppressWarnings("unused")
public class SuiviLogistiqueDto {
    private Integer id;
    private String reference;
    private String designation;
    private String nomProjet;
    private String nomSousProjet;
    private String codeFournisseur;
    private String nomFournisseur;
    private String utilisateurPSA;
    private String magasin;
    private String site;
    private StatutLogistique statut;
    private Integer quantiteEcheancee;
    private Integer quantiteLivree;
    private Integer quantiteReliquat;           // NEW
    private LocalDate dateEcheance;
    private LocalDate dateLivraisonConfirmee;
    private Boolean escaladeProjet;              // NEW
    private String statutQualite;                // NEW
    private String motifRefusQualite;            // NEW
    private String statutBl;                     // NEW
    private String numeroBl;                     // NEW (SAP)
    private String numeroTracking;               // NEW (SAP)
    private String dernierCommentaire;
    private String dernierPplRlog;

    public SuiviLogistiqueDto() {
        // TODO document why this constructor is empty
    }
}