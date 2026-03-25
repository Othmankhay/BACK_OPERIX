package com.stellantis.operix.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {

    public void genererAlertes() {
        // Escalades non traitées > 48h
        suiviRepo.findByEscaladeProjetTrue().forEach(s ->
                creer("critical", "Escalade non traitée",
                        s.getArticle().getReference()));
        suiviRepo.findByStatutBl("anomalie").forEach(s ->
                creer("warning", "Anomalie BL",
                        s.getArticle().getReference()));
        suiviRepo.findByStatutQualite("nok").forEach(s ->
                creer("critical", "Contrôle qualité NOK",
                        s.getArticle().getReference()));
    }
}