package com.stellantis.operix.controller;

import com.stellantis.operix.dto.fournisseur.FicheFournisseurDto;
import com.stellantis.operix.service.FournisseurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fournisseurs")
@RequiredArgsConstructor
public class FournisseurController {

    private final FournisseurService service;

    @GetMapping("/{id}/fiche")
    public ResponseEntity<FicheFournisseurDto> fiche(
            @PathVariable Integer id) {
        return ResponseEntity.ok(service.getFiche(id));
    }
}
