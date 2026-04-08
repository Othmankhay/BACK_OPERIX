package com.stellantis.operix.service;

import com.stellantis.operix.enums.StatutLogistique;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StatutCalculatorService {

    public StatutLogistique calculer(
            Integer quantiteEcheancee,
            Integer quantiteLivree,
            LocalDate dateEcheance,
            boolean fauxManquant,
            boolean escaladeProjet,
            String statutQualite,
            String statutBl) {
        if (fauxManquant) {
            return StatutLogistique.FAUX_MANQUANT;
        }
        if (quantiteEcheancee != null && quantiteLivree != null && quantiteLivree >= quantiteEcheancee) {
            return StatutLogistique.RECU;
        }
        if (dateEcheance != null && dateEcheance.isBefore(LocalDate.now())) {
            return StatutLogistique.RETARD;
        }
        return StatutLogistique.EN_COURS;
    }
}
