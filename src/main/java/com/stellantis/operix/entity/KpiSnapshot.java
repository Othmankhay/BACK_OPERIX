package com.stellantis.operix.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Id;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "kpi_snapshots")
@Getter
@Setter
public class KpiSnapshot {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private LocalDate dateSnapshot;
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;
    private Integer nbManquantsPlus, nbPointDur, nbRetard;
    private Integer nbRecu, nbEnCours, totalArticles;
    private Integer totalQteEcheancee, totalQteLivree;
    private BigDecimal tauxOtd;
    private LocalDateTime createdAt;
}