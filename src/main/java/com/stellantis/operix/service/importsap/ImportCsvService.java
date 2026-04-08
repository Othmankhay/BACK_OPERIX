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
        int imported = 0;
        int errors = 0;
        try (CSVReader reader = new CSVReader(new InputStreamReader(in))) {
            String[] line;
            boolean header = true;
            while ((line = reader.readNext()) != null) {
                if (header) {
                    header = false;
                    continue;
                }
                if (line.length == 0) {
                    errors++;
                    continue;
                }
                imported++;
            }
        } catch (Exception e) {
            errors++;
        }
        return new ImportResultDto(imported, errors, errors == 0 ? "succes" : "partiel");
    }
}