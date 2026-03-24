package com.stellantis.operix.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;
import org.hibernate.sql.ast.tree.expression.JsonTableColumnDefinition;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "roles")


public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)

    private Integer id;

    @Column(nullable = false, unique = true)

    private String nom;
    private String description;

    @Column(ColumnDefinition = jsonb)

    private String permissions;
    private LocalDataTime createdAt;
}


