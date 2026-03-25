package com.stellantis.operix.service.importsap;

import com.opencsv.CSVReader;
import com.stellantis.operix.dto.importsap.ImportResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;

@Service
@RequiredArgsConstructor
public class ImportCsvService
        implements ImportService {

    public boolean supports(String t) {
        return "csv".equals(t);
    }

    public ImportResultDto importer(
            InputStream in, String fileName) {
        CSVReader reader = new CSVReader(
                new InputStreamReader(in));
        // Parser chaque ligne CSV
        // Mapper vers SuiviLogistique
        // Calculer statut
        // Sauvegarder
    }
}