package com.stellantis.operix.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

public class KpiController {
    @RestController
    @RequestMapping("/api/kpi")
    @RequiredArgsConstructor
    public class KpiController {
        @GetMapping("/courant")
        public ResponseEntity<KpiCourantDto> courant() {
            return ResponseEntity.ok(kpiService.getKpiCourant());
        }

        @GetMapping("/otd")
        public ResponseEntity<OtdTableauDto> otd(
                @RequestParam(required = false) Integer projetId) {
            return ResponseEntity.ok(
                    kpiService.getOtdTableau(projetId));
        }
    }}