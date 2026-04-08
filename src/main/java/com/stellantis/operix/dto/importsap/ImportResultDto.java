package com.stellantis.operix.dto.importsap;

public class ImportResultDto {
    private Integer lignesImportees;
    private Integer lignesErreur;
    private String statut; // succes|partiel|erreur
    private String message;

    public ImportResultDto(Integer lignesImportees, Integer lignesErreur, String statut, String message) {
        this.lignesImportees = lignesImportees;
        this.lignesErreur = lignesErreur;
        this.statut = statut;
        this.message = message;
    }

    public ImportResultDto(int imported, int errors, String statut) {
        this.lignesImportees = imported;
        this.lignesErreur = errors;
        this.statut = statut;
        this.message = null;
    }

    public Integer getLignesImportees() {
        return lignesImportees;
    }

    public Integer getLignesErreur() {
        return lignesErreur;
    }

    public String getStatut() {
        return statut;
    }

    public String getMessage() {
        return message;
    }
}