package com.stellantis.operix.scheduler;

import com.stellantis.operix.dto.importsap.ImportResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ImportDossierScheduler {

    @Scheduled(cron = "${operix.sap.cron}")
    public void importerSAP() {
        log.info("[SAP] Import démarré");
        ImportResultDto r =
                importDossierService.traiterDossier();
        log.info("[SAP] {} lignes, {} erreurs",
                r.getLignesImportees(),
                r.getLignesErreur());
        suiviService.recalculerTousLesStatuts();
        notifService.genererAlertes();
    }
}