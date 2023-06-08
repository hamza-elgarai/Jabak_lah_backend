package com.example.jl_entities.entity;

import com.example.jl_entities.serializer.HibernateProxySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(exclude = "formulaire")
@AllArgsConstructor
@NoArgsConstructor
public class Creance implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Column(unique = true ,nullable = false)
    private String code;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(using = HibernateProxySerializer.class)
    private Creancier creancier;
    @OneToOne(fetch = FetchType.EAGER)
    private Formulaire formulaire;
}
