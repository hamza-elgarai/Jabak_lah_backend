package com.example.jl_entities.entity;

import com.example.jl_entities.serializer.HibernateProxySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
public class Impaye implements Serializable {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private Double price;
    private String type;
    private Boolean isPaid;
    private Long date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(using = HibernateProxySerializer.class)
    private Creance creance;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "impaye")
    List<ImpayeCredential> credentials;
}
