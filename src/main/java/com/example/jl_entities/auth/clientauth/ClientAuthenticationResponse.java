package com.example.jl_entities.auth.clientauth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientAuthenticationResponse {

    private String token;
    private String refreshToken;
}
