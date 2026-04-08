package com.stellantis.operix.scheduler;

import com.stellantis.operix.dto.importsap.ImportResultDto;
import com.stellantis.operix.service.NotificationService;
import com.stellantis.operix.service.SuiviLogistiqueService;
import com.stellantis.operix.service.importsap.ImportDossierService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImportDossierScheduler {

    private final ImportDossierService importDossierService;
    private final SuiviLogistiqueService suiviService;
    private final NotificationService notifService;

    @Scheduled(cron = "${operix.sap.cron}")
    public void importerSAP() {
        ImportResultDto r =
                importDossierService.traiterDossier();
        suiviService.recalculerTousLesStatuts();
        notifService.genererAlertes();
    }
}