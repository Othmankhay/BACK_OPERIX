package com.stellantis.operix.repository;

import com.stellantis.operix.entity.KpiSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface KpiSnapshotRepository
        extends JpaRepository<KpiSnapshot, Integer> {
    List<KpiSnapshot> findByProjetIdAndDateSnapshotBetween(
            Integer projetId, LocalDate from, LocalDate to);
    Optional<KpiSnapshot> findByDateSnapshot(LocalDate date);
}