package com.stellantis.operix.dto.importsap;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImportResultDto {
    private Integer lignesImportees;
    private Integer lignesErreur;
    private String statut; // succes|partiel|erreur
    private String message;

    public ImportResultDto(int imported, int errors, String statut) {
    }
}