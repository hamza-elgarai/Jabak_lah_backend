package com.example.jl_entities.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompteBancaire implements Serializable {
    @Id@GeneratedValue
    private Long id;
    private String numeroCompte;
    private Double solde;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Operation> operations;
}
