package com.stellantis.operix.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class KpiSnapshotScheduler {

    @Scheduled(cron = "${operix.kpi.cron}")
    public void genererSnapshot() {
        log.info("[KPI] Génération snapshot {}",
                LocalDate.now());
        kpiSnapshotService.genererSnapshotDuJour();
        log.info("[KPI] Snapshot terminé");
    }
}