package com.stellantis.operix.service.importsap;


import com.stellantis.operix.dto.importsap.ImportResultDto;
import com.stellantis.operix.service.importsap.ImportService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImportDossierService
        implements ImportService {

    @Value("${operix.sap.import-folder}")
    private String importFolder;

    @Value("${operix.sap.archive-folder}")
    private String archiveFolder;

    public ImportDossierService(String archiveFolder) {
        this.archiveFolder = archiveFolder;
    }

    public ImportResultDto traiterDossier() {
        return Files.list(Paths.get(importFolder))
                .filter(p -> !dejaTraite(p))
                .map(p -> {
                    String type = p.toString()
                            .endsWith(".csv") ? "csv" : "excel";
                    ImportResultDto r = type.equals("csv")
                            ? csvService.importer(...)
                    : excelService.importer(...);
                    archiver(p);
                    return r;
                })
                .reduce(this::merge)
                .orElse(new ImportResultDto(0, 0, "succes"));
    }

    public String getArchiveFolder() {
        return archiveFolder;
    }

    public void setArchiveFolder(String archiveFolder) {
        this.archiveFolder = archiveFolder;
    }
}