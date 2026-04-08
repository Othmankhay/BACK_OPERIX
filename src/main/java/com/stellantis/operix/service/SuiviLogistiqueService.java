package com.stellantis.operix.service;

import com.stellantis.operix.dto.suivi.SuiviFilterDto;
import com.stellantis.operix.dto.suivi.SuiviLogistiqueDto;
import com.stellantis.operix.entity.SuiviLogistique;
import com.stellantis.operix.enums.StatutLogistique;
import com.stellantis.operix.exception.ResourceNotFoundException;
import com.stellantis.operix.mapper.SuiviLogistiqueMapper;
import com.stellantis.operix.repository.SuiviLogistiqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SuiviLogistiqueService {

    private final SuiviLogistiqueRepository suiviRepo;
    private final SuiviLogistiqueMapper mapper;

    public Page<SuiviLogistiqueDto> findAll(SuiviFilterDto filter, Pageable pageable) {
        StatutLogistique statut = parseStatut(filter.getStatut());
        return suiviRepo.findAllWithFilters(
                filter.getProjet(),
                statut,
                filter.getFournisseur(),
                filter.getSearch(),
                pageable
        ).map(mapper::toDto);
    }

    private StatutLogistique parseStatut(String statut) {
        if (statut == null || statut.isBlank()) {
            return null;
        }
        try {
            return StatutLogistique.valueOf(statut.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    public SuiviLogistiqueDto updateEscalade(Integer id, boolean escalade, String commentaire) {
        SuiviLogistique suivi = suiviRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suivi introuvable"));
        suivi.setEscaladeProjet(escalade);
        return mapper.toDto(suiviRepo.save(suivi));
    }

    public SuiviLogistiqueDto updateQualite(Integer id, String statut, String motif) {
        SuiviLogistique suivi = suiviRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suivi introuvable"));
        suivi.setStatutQualite(statut);
        suivi.setMotifRefusQualite(motif);
        return mapper.toDto(suiviRepo.save(suivi));
    }

    public void recalculerTousLesStatuts() {
        // Placeholder: recalcul métier à implémenter selon règles SAP.
    }
}

