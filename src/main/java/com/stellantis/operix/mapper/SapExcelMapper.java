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

    public SuiviLogistique mapRow(Row row) {
        return new SuiviLogistique();
    }
}