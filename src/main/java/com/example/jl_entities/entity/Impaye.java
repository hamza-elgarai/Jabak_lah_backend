package com.example.jl_entities.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Impaye implements Serializable {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private Double price;
    private String type;
    private Boolean isPaid;
    private Date date;
    @ManyToOne
    private Creance creance;
}
