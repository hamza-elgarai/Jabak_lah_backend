package com.example.jl_entities.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Creancier {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true,nullable = false)
    private String code;
    private String name;
    private String logoUrl;

    /*@OneToMany(mappedBy = "creancier")
    List<Creance> creanceList;*/


}
