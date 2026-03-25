package com.stellantis.operix.service;

@Service @RequiredArgsConstructor
public class FournisseurService {

    public FicheFournisseurDto getFiche(Integer id) {
        Fournisseur f = fournisseurRepo.findById(id).orElseThrow();
        List<SuiviLogistique> pieces =
                suiviRepo.findByArticleFournisseurId(id);
        int total = pieces.size();
        int livrees = (int) pieces.stream()
                .filter(p -> p.getStatut() == RECU).count();
        double otd = total > 0
                ? (double) livrees / total * 100 : 0;
        Map<StatutLogistique, Long> repartition =
                pieces.stream().collect(
                        groupingBy(SuiviLogistique::getStatut, counting()));
        return FicheFournisseurDto.builder()
                .nom(f.getNom()).totalPieces(total)
                .tauxLivraison(otd)
                .repartitionStatuts(repartition)
                .build();
    }
}