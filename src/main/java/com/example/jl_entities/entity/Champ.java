package com.example.jl_entities.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Champ implements Serializable {
    @Id @GeneratedValue
    private Long id;
    private String type;
    private String name;
    private String value;
    private String label;
    @ManyToOne
    private Formulaire formulaire;
}
