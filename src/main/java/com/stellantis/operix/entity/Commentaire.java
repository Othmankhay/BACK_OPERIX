package com.stellantis.operix.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "commentaires")
@Getter
@Setter
public class Commentaire {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @ManyToOne @JoinColumn(name = "article_id")
    private Article article;
    @ManyToOne
    @JoinColumn(name = "auteur_id")
    private Utilisateur auteur;
    private String type;    // commentaire | ppl_rlog
    private String contenu;
    private LocalDateTime createdAt, updatedAt;
}
