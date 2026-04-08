package com.stellantis.operix.service;

import com.stellantis.operix.dto.fournisseur.FicheFournisseurDto;
import org.springframework.stereotype.Service;

@Service
public class FournisseurService {

    public FicheFournisseurDto getFiche(Integer id) {
        FicheFournisseurDto dto = new FicheFournisseurDto();
        dto.setTotalPieces(0);
        dto.setTauxLivraison(0d);
        return dto;
    }
}