package com.example.jl_entities.entity;

import com.example.jl_entities.serializer.HibernateProxySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImpayeCredential implements Serializable {
    @Id@GeneratedValue
    private Long id;
    private String credName;
    private String credValue;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(using = HibernateProxySerializer.class)
    private Impaye impaye;

}
