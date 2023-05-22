package com.example.jl_entities.entity;

import jakarta.persistence.*;
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
