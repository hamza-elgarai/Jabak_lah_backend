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
public class Creance implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Column(unique = true ,nullable = false)
    private String code;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Creancier creancier;
}
