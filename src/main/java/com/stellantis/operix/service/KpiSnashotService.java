package com.stellantis.operix.service;

import lombok.RequiredArgsConstructor;
import org.apache.el.stream.Stream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.stellantis.operix.enums.StatutLogistique.*;

@Service
@Transactional
@RequiredArgsConstructor
public class KpiSnapshotService<KpiCourantDto> {

    private Stream suiviRepo;

    public KpiCourantDto getKpiCourant() {
        long total      = suiviRepo.count();
        long retards    = suiviRepo.countByStatut(RETARD);
        long manquants  = suiviRepo.countByStatut(MANQUANTS_PLUS);
        long recus      = suiviRepo.countByStatut(RECU);
        double otd      = total > 0
                ? (double) recus / total * 100 : 0;
        return KpiCourantDto.builder()
                .total((int) total).retards((int) retards)
                .manquants((int) manquants).otd(otd).build();
    }

    public void genererSnapshotDuJour() {
        projetRepo.findAll().forEach(projet -> {
            
        });
    }
}