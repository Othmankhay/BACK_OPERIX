package com.stellantis.operix.service.importsap;

import com.stellantis.operix.dto.importsap.ImportResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ImportDossierService
        implements ImportService {

    @Value("${operix.sap.import-folder}")
    private String importFolder;

    @Value("${operix.sap.archive-folder}")
    private String archiveFolder;

    private final ImportCsvService csvService;
    private final ImportExcelService excelService;

    public ImportResultDto traiterDossier() {
        try (Stream<Path> files = Files.list(Paths.get(importFolder))) {
            return files
                    .filter(p -> !dejaTraite(p))
                    .map(this::importerFichier)
                    .reduce(this::merge)
                    .orElse(new ImportResultDto(0, 0, "succes"));
        } catch (IOException e) {
            return new ImportResultDto(0, 1, "erreur");
        }
    }

    private ImportResultDto importerFichier(Path p) {
        try {
            String type = p.toString().endsWith(".csv") ? "csv" : "excel";
            try (var in = Files.newInputStream(p)) {
                ImportResultDto result = "csv".equals(type)
                        ? csvService.importer(in, p.getFileName().toString())
                        : excelService.importer(in, p.getFileName().toString());
                archiver(p);
                return result;
            }
        } catch (Exception e) {
            return new ImportResultDto(0, 1, "erreur");
        }
    }

    private ImportResultDto merge(ImportResultDto left, ImportResultDto right) {
        int imported = (left.getLignesImportees() == null ? 0 : left.getLignesImportees())
                + (right.getLignesImportees() == null ? 0 : right.getLignesImportees());
        int errors = (left.getLignesErreur() == null ? 0 : left.getLignesErreur())
                + (right.getLignesErreur() == null ? 0 : right.getLignesErreur());
        return new ImportResultDto(imported, errors, errors == 0 ? "succes" : "partiel");
    }

    private boolean dejaTraite(Path p) {
        return false;
    }

    private void archiver(Path p) {
        // TODO: brancher l'archivage réel une fois la politique de nommage définie.
    }

    @Override
    public ImportResultDto importer(java.io.InputStream inputStream, String originalFilename) {
        return traiterDossier();
    }

    public String getArchiveFolder() {
        return archiveFolder;
    }

    public void setArchiveFolder(String archiveFolder) {
        this.archiveFolder = archiveFolder;
    }
}