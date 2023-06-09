package com.example.jl_entities.entity;

import com.example.jl_entities.serializer.HibernateProxySerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paiement implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Long timestamp;
    private String numeroCarte;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(using = HibernateProxySerializer.class)
    @ToString.Exclude
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(using = HibernateProxySerializer.class)
    @JoinColumn(nullable = false)
    private Impaye impaye;


}
