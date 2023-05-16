package com.example.jl_entities.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompteBancaire implements Serializable {
    @Id@GeneratedValue
    private Long id;
    private String numeroCompte;
    private Double solde;
}
