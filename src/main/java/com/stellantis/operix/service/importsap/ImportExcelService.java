package com.stellantis.operix.service.importsap;

import com.stellantis.operix.dto.importsap.ImportResultDto;
import com.stellantis.operix.entity.SuiviLogistique;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImportExcelService
        implements ImportService {

    public boolean supports(String t) {
        return "excel".equals(t);
    }

    public ImportResultDto importer(
            InputStream in, String fileName) {
        Workbook wb = new XSSFWorkbook(in);
        int imported = 0, errors = 0;
        for (Row row : wb.getSheetAt(0)) {
            if (row.getRowNum() == 0) continue;
            try {
                SuiviLogistique s = mapper.mapRow(row);
                s.setStatut(statutCalc.calculer(...));
                suiviRepo.save(s);
                imported++;
            } catch (Exception e) { errors++; }
        }
        return new ImportResultDto(imported, errors,
                errors == 0 ? "succes" : "partiel");
    }
}
