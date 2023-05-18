package com.example.jl_entities.auth.agentauth.provider;

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
        // Retrieve the authentication credentials (e.g., username and password)
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Implement your custom authentication logic for the CustomEntity here
        // Authenticate the entity and create an appropriate authentication object

        // Return an Authentication object representing the authenticated entity
        // For example, you can use the UsernamePasswordAuthenticationToken class
        // to encapsulate the authenticated entity's details
        return new UsernamePasswordAuthenticationToken(username, password, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // Indicate that this provider supports authentication with CustomEntity
        return authentication.equals(Agent.class);
    }
}