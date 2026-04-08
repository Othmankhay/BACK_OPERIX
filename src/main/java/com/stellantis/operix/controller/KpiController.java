package com.stellantis.operix.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/kpi")
public class KpiController {

    @GetMapping("/courant")
    public ResponseEntity<Map<String, Object>> courant() {
        return ResponseEntity.ok(Map.of("total", 0, "retards", 0, "manquants", 0, "otd", 0d));
    }

    @GetMapping("/otd")
    public ResponseEntity<Map<String, Object>> otd(
            @RequestParam(required = false) Integer projetId) {
        return ResponseEntity.ok(Map.of("projetId", projetId, "valeurs", java.util.List.of()));
    }
}
