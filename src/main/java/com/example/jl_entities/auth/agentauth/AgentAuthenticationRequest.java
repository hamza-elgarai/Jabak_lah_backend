package com.example.jl_entities.auth.agentauth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgentAuthenticationRequest {

    private String username;
    String password;
}
