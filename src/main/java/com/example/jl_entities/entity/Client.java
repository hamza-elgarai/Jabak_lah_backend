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
public class Client implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String fname;
    private String lname;
    private String email;
    private String tel;
    private String password;
    private String numeroCompte;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = AccountType.class)
    private AccountType type;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "client")
    List<Paiement> paiements;

    private Double solde;
    private String verificationStatus;
}
