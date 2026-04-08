package com.stellantis.operix.scheduler;

import com.stellantis.operix.service.KpiSnashotService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KpiSnaphsotScheduler {

    private final KpiSnashotService kpiSnapshotService;

    @Scheduled(cron = "${operix.kpi.cron}")
    public void genererSnapshot() {
        kpiSnapshotService.genererSnapshotDuJour();
    }
}