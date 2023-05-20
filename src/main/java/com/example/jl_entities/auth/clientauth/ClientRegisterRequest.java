package com.example.jl_entities.auth.clientauth;

import com.example.jl_entities.entity.AccountType;
import com.example.jl_entities.entity.Paiement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRegisterRequest {

    private String fname;
    private String lname;
    private String email;
    private String tel;
    private String password;
    private Long idType;

}
