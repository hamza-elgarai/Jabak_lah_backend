package com.example.jl_entities.auth.provider;

import com.example.jl_entities.entity.Agent;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AgentAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        return new UsernamePasswordAuthenticationToken(username, password, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(Agent.class);
    }
}