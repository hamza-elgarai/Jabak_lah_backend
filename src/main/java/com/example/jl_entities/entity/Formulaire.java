package com.example.jl_entities.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Formulaire implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Champ> champs;
}
