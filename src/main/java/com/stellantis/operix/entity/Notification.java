package com.stellantis.operix.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "notifications")
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String type;   // critical|warning|info|success
    private String titre, message, lienStatut;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
    @ManyToOne @JoinColumn(name = "destinataire_id")
    private Utilisateur destinataire;
    private Boolean lue = false;
    private LocalDateTime lueLe, createdAt;
}