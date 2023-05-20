package com.example.jl_entities.auth.bodies.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;
}
