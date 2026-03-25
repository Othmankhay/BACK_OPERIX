package com.stellantis.operix.controller;

import com.stellantis.operix.dto.fournisseur.FicheFournisseurDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fournisseurs")
@RequiredArgsConstructor
public class FournisseurController {

    @GetMapping
    public ResponseEntity<List<FournisseurDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}/fiche")
    public ResponseEntity<FicheFournisseurDto> fiche(
            @PathVariable Integer id) {
        return ResponseEntity.ok(service.getFiche(id));
    }
}
