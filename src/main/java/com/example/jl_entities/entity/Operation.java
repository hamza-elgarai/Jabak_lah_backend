package com.example.jl_entities.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    @Id@GeneratedValue
    private Long id;
    private String type;
    private String description;
    private Long date;
    private Double amount;
    @ManyToOne
    private CompteBancaire compteBancaire;
}
