package com.stellantis.operix.dto.fournisseur;

import com.stellantis.operix.dto.suivi.SuiviLogistiqueDto;
import com.stellantis.operix.enums.StatutLogistique;
import com.sun.tools.javac.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class FicheFournisseurDto {
    private String code, nom;
    private Integer totalPieces;
    private Integer totalQteEcheancee, totalQteLivree;
    private Double tauxLivraison;
    private Map<StatutLogistique, Long> repartitionStatuts;
    private List<SuiviLogistiqueDto> pieces;
}
