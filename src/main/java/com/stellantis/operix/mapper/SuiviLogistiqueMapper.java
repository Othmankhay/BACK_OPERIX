package com.stellantis.operix.mapper;

import com.stellantis.operix.dto.suivi.SuiviLogistiqueDto;
import com.stellantis.operix.entity.SuiviLogistique;
import org.springframework.stereotype.Component;

@Component
public class SuiviLogistiqueMapper {

    public SuiviLogistiqueDto toDto(SuiviLogistique s) {
        return new SuiviLogistiqueDto();
    }
}