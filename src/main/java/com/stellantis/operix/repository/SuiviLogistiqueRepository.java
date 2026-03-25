package com.stellantis.operix.repository;

import com.stellantis.operix.entity.SuiviLogistique;
import com.stellantis.operix.enums.StatutLogistique;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface SuiviLogistiqueRepository
        extends JpaRepository<SuiviLogistique, Integer> {

    @Query("""
        SELECT s FROM SuiviLogistique s
        LEFT JOIN s.article a
        LEFT JOIN a.fournisseur f
        LEFT JOIN a.sousProjet sp
        LEFT JOIN sp.projet p
        WHERE (:projet IS NULL OR p.nom = :projet)
        AND (:statut IS NULL OR s.statut = :statut)
        AND (:fournisseur IS NULL OR f.nom = :fournisseur)
        AND (:search IS NULL OR
             LOWER(a.reference) LIKE LOWER(CONCAT('%',:search,'%')))
        """)
    Page<SuiviLogistique> findAllWithFilters(
            @Param("projet") String projet,
            @Param("statut") String statut,
            @Param("fournisseur") String fournisseur,
            @Param("search") String search,
            Pageable pageable);

    long countByStatut(StatutLogistique statut);
}
