package com.stellantis.operix.service.importsap;

import com.stellantis.operix.dto.importsap.ImportResultDto;
import com.stellantis.operix.entity.SuiviLogistique;
import com.stellantis.operix.enums.StatutLogistique;
import com.stellantis.operix.repository.SuiviLogistiqueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImportExcelService implements ImportService {

    private final SuiviLogistiqueRepository suiviRepository;
    private final DataFormatter formatter = new DataFormatter(Locale.ROOT);

    @Override
    public boolean supports(String t) {
        return "excel".equals(t);
    }

    @Override
    @Transactional
    public ImportResultDto importer(InputStream in, String fileName) {
        int imported = 0;
        int errors = 0;
        List<SuiviLogistique> aEnregistrer = new ArrayList<>();

        try (Workbook wb = new XSSFWorkbook(in)) {
            if (wb.getNumberOfSheets() == 0) {
                return new ImportResultDto(0, 1, "erreur", "Aucune feuille Excel trouvée");
            }
            Sheet sheet = wb.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                return new ImportResultDto(0, 1, "erreur", "Ligne d'en-tête manquante");
            }
            Map<String, Integer> headerIndex = buildHeaderIndex(headerRow);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                if (isRowEmpty(row)) continue;

                try {
                    SuiviLogistique suivi = new SuiviLogistique();

                    String statutRaw = readString(row, headerIndex, "statut", 0);
                    if (statutRaw != null) {
                        suivi.setStatut(parseStatut(statutRaw));
                    }

                    suivi.setQuantiteEcheancee(readInteger(row, headerIndex, "quantiteecheancee", 1));
                    suivi.setDateEcheance(readLocalDate(row, headerIndex, "dateecheance", 2));

                    Boolean escalade = readBoolean(row, headerIndex, "escaladeprojet", 3);
                    if (escalade != null) {
                        suivi.setEscaladeProjet(escalade);
                    }

                    suivi.setStatutQualite(readString(row, headerIndex, "statutqualite", 4));
                    suivi.setMotifRefusQualite(readString(row, headerIndex, "motifrefusqualite", 5));

                    aEnregistrer.add(suivi);
                    imported++;
                } catch (Exception e) {
                    log.error("Erreur sur la ligne {} du fichier {}", row.getRowNum() + 1, fileName, e);
                    errors++;
                }
            }

            if (!aEnregistrer.isEmpty()) {
                suiviRepository.saveAll(aEnregistrer);
            }

        } catch (Exception e) {
            log.error("Erreur lors de l'import Excel {}", fileName, e);
            errors++;
        }

        return new ImportResultDto(imported, errors, errors == 0 ? "succes" : "partiel");
    }

    private Map<String, Integer> buildHeaderIndex(Row headerRow) {
        Map<String, Integer> headerIndex = new HashMap<>();
        short last = headerRow.getLastCellNum();
        for (int i = 0; i < last; i++) {
            Cell cell = headerRow.getCell(i);
            String raw = cell == null ? "" : formatter.formatCellValue(cell);
            if (!raw.isBlank()) {
                headerIndex.put(normalize(raw), i);
            }
        }
        return headerIndex;
    }

    private boolean isRowEmpty(Row row) {
        int last = row.getLastCellNum();
        for (int i = 0; i < last; i++) {
            Cell cell = row.getCell(i);
            if (cell != null && !formatter.formatCellValue(cell).trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private String readString(Row row, Map<String, Integer> headerIndex, String headerKey, int fallbackIndex) {
        Cell cell = findCell(row, headerIndex, headerKey, fallbackIndex);
        if (cell == null) return null;
        String value = formatter.formatCellValue(cell).trim();
        return value.isEmpty() ? null : value;
    }

    private Integer readInteger(Row row, Map<String, Integer> headerIndex, String headerKey, int fallbackIndex) {
        Cell cell = findCell(row, headerIndex, headerKey, fallbackIndex);
        if (cell == null) return null;

        if (cell.getCellType() == CellType.NUMERIC) {
            return (int) Math.round(cell.getNumericCellValue());
        }

        String raw = formatter.formatCellValue(cell).trim();
        if (raw.isEmpty()) return null;
        raw = raw.replace(" ", "").replace(",", ".");
        try {
            if (raw.contains(".")) {
                return (int) Math.round(Double.parseDouble(raw));
            }
            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valeur entière invalide: " + raw);
        }
    }

    private Boolean readBoolean(Row row, Map<String, Integer> headerIndex, String headerKey, int fallbackIndex) {
        Cell cell = findCell(row, headerIndex, headerKey, fallbackIndex);
        if (cell == null) return null;

        if (cell.getCellType() == CellType.BOOLEAN) {
            return cell.getBooleanCellValue();
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            return Math.round(cell.getNumericCellValue()) != 0;
        }

        String raw = formatter.formatCellValue(cell).trim().toLowerCase(Locale.ROOT);
        if (raw.isEmpty()) return null;
        if ("true".equals(raw) || "1".equals(raw) || "oui".equals(raw) || "yes".equals(raw)) return true;
        if ("false".equals(raw) || "0".equals(raw) || "non".equals(raw) || "no".equals(raw)) return false;
        throw new IllegalArgumentException("Valeur booléenne invalide: " + raw);
    }

    private LocalDate readLocalDate(Row row, Map<String, Integer> headerIndex, String headerKey, int fallbackIndex) {
        Cell cell = findCell(row, headerIndex, headerKey, fallbackIndex);
        if (cell == null) return null;

        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getLocalDateTimeCellValue().toLocalDate();
        }

        String raw = formatter.formatCellValue(cell).trim();
        if (raw.isEmpty()) return null;
        for (DateTimeFormatter f : List.of(
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("d/M/yyyy")
        )) {
            try {
                return LocalDate.parse(raw, f);
            } catch (DateTimeParseException ignored) {
                // Try next known date format.
            }
        }
        throw new IllegalArgumentException("Date invalide: " + raw);
    }

    private Cell findCell(Row row, Map<String, Integer> headerIndex, String headerKey, int fallbackIndex) {
        Integer index = headerIndex.get(normalize(headerKey));
        if (index == null) {
            index = fallbackIndex;
        }
        return row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
    }

    private StatutLogistique parseStatut(String value) {
        String normalized = normalize(value).toUpperCase(Locale.ROOT);
        try {
            return StatutLogistique.valueOf(normalized);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Statut invalide: " + value);
        }
    }

    private String normalize(String value) {
        return value == null
                ? ""
                : value.trim()
                .replace('-', '_')
                .replace(' ', '_')
                .replace("é", "e")
                .replace("è", "e")
                .replace("ê", "e")
                .replace("à", "a")
                .replace("ù", "u")
                .replace("ç", "c")
                .toLowerCase(Locale.ROOT);
    }
}
