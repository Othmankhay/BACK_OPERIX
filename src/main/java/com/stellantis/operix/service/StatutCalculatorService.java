package com.stellantis.operix.service;


import com.stellantis.operix.dto.suivi.SuiviFilterDto;
import com.stellantis.operix.dto.suivi.SuiviLogistiqueDto;
import com.stellantis.operix.entity.SuiviLogistique;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SuiviLogistiqueService {

    public Page<SuiviLogistiqueDto> findAll(
            SuiviFilterDto filter, Pageable pageable) {
        return suiviRepo.findAllWithFilters(
                filter.getProjet(), filter.getStatut(),
                filter.getFournisseur(), filter.getSearch(),
                pageable).map(mapper::toDto);
    }

    public void recalculerTousLesStatuts() {
        List<SuiviLogistique> all = suiviRepo.findAll();
        all.forEach(s -> s.setStatut(
                statutCalc.calculer(
                        s.getQuantiteEcheancee(),
                        s.getQuantiteLivree(),
                        s.getDateEcheance(),
                        s.isFauxManquant(),
                        s.isEscaladeProjet(),
                        s.getStatutQualite(),
                        s.getStatutBl())));
        suiviRepo.saveAll(all);
        notifService.genererAlertes();
    }
}
