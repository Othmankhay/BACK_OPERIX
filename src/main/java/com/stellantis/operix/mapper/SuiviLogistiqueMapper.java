package com.stellantis.operix.mapper;

import com.stellantis.operix.dto.suivi.SuiviLogistiqueDto;
import com.stellantis.operix.entity.Commentaire;
import com.stellantis.operix.entity.SuiviLogistique;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SuiviLogistiqueMapper {

    public SuiviLogistiqueDto toDto(SuiviLogistique s) {
        String dernierCommentaire =
                commentaireRepo
                        .findTopByArticleIdAndTypeOrderByCreatedAtDesc(
                                s.getArticle().getId(), "commentaire")
                        .map(Commentaire::getContenu).orElse(null);

        return SuiviLogistiqueDto.builder()
                .id(s.getId())
                .reference(s.getArticle().getReference())
                .designation(s.getArticle().getDesignation())
                .nomProjet(s.getArticle().getSousProjet()
                        .getProjet().getNom())
                .nomFournisseur(s.getArticle()
                        .getFournisseur().getNom())
                .statut(s.getStatut())
                .quantiteEcheancee(s.getQuantiteEcheancee())
                .quantiteLivree(s.getQuantiteLivree())
                .quantiteReliquat(s.getQuantiteReliquat())
                .escaladeProjet(s.getEscaladeProjet())
                .statutQualite(s.getStatutQualite())
                .statutBl(s.getStatutBl())
                .dernierCommentaire(dernierCommentaire)
                .build();
    }
}