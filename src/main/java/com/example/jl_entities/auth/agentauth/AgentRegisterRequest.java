package com.example.jl_entities.auth.agentauth;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgentRegisterRequest {

    private String fname;
    private String lname;
    private String identityType;
    private String identityNumber;
    private String birthday;
    private String address;
    private String tel;
    private String password;
    private String immatriculation;
    private String licenseNumber;
    private String idFilePath;
    private Long agencyId;
}
