package com.stellantis.operix.controller;

import com.stellantis.operix.dto.suivi.SuiviFilterDto;
import com.stellantis.operix.dto.suivi.SuiviLogistiqueDto;
import com.stellantis.operix.service.SuiviLogistiqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suivi")
@RequiredArgsConstructor
public class SuiviLogistiqueController {

    private final SuiviLogistiqueService service;

    @GetMapping
    public ResponseEntity<Page<SuiviLogistiqueDto>> findAll(
            @ModelAttribute SuiviFilterDto filter,
            @PageableDefault(size = 25) Pageable pageable) {
        return ResponseEntity.ok(
                service.findAll(filter, pageable));
    }

    @PutMapping("/{id}/escalade")
    public ResponseEntity<SuiviLogistiqueDto> escalade(
            @PathVariable Integer id,
            @RequestBody EscaladeRequest req) {
        return ResponseEntity.ok(
                service.updateEscalade(id,
                        req.escalade(), req.commentaire()));
    }

    @PutMapping("/{id}/qualite")
    public ResponseEntity<SuiviLogistiqueDto> qualite(
            @PathVariable Integer id,
            @RequestBody QualiteRequest req) {
        return ResponseEntity.ok(
                service.updateQualite(id,
                        req.statut(), req.motif()));
    }

    public record EscaladeRequest(boolean escalade, String commentaire) {
    }

    public record QualiteRequest(String statut, String motif) {
    }
}