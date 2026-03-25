package com.stellantis.operix.mapper;

import com.stellantis.operix.dto.suivi.SuiviLogistiqueDto;
import com.stellantis.operix.entity.Article;
import com.stellantis.operix.entity.Commentaire;
import com.stellantis.operix.entity.SuiviLogistique;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component @RequiredArgsConstructor


public class SapExcelMapper {

    private static final DateTimeFormatter SAP_DATE =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public SuiviLogistique mapRow(Row row) {
        SuiviLogistique s = new SuiviLogistique();
        // MATNR → Article
        String matnr = getCellString(row, 2);
        Article article = articleRepo
                .findByReference(matnr)
                .orElseGet(() -> creerArticle(row));
        s.setArticle(article);
        // MENGE → quantiteEcheancee
        s.setQuantiteEcheancee(
                (int) Double.parseDouble(
                        getCellString(row, 6)));
        // EINDT → dateEcheance (format SAP: JJ.MM.AAAA)
        String date = getCellString(row, 8);
        if (!date.isBlank())
            s.setDateEcheance(
                    LocalDate.parse(date, SAP_DATE));
        return s;
    }
}