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
public class Agent implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String fname;
    @Column(nullable = false)
    private String lname;
    @Column(nullable = false)
    private String identityType;
    @Column(nullable = false)
    private String identityNumber;
    @Column(nullable = false)
    private Date birthday;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String tel;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String immatriculation;
    @Column(nullable = false)
    private String licenseNumber;
    @Column(nullable = false)
    private String idFilePath;
    @ManyToOne
    private Agency agency;

}
