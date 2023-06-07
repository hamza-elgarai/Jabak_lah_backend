package com.example.jl_entities.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserDetailsService agentDetailsService;
    private final UserDetailsService clientDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        String username = null;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);

        //Test if token is expired
        try{
            username = jwtService.extractUsername(jwt);
        }catch (ExpiredJwtException expiredJwtException){
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
            return;
        }

        List<String> roles = jwtService.extractRoles(jwt);

        //If a user tries to access using refresh token
        if(roles==null){
            System.out.println("Refresh token haha");
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "This is a refresh token");
            return;
        }

        String role = roles.get(0);
        UserDetails userDetails;

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            if(role.equals("USER")){
                System.out.println("USER detected in filter!");
                userDetails = this.userDetailsService.loadUserByUsername(username);
            } else if (role.equals("AGENT")) {
                System.out.println("AGENT detected in filter!");
                userDetails = this.agentDetailsService.loadUserByUsername(username);
            } else if (role.equals("CLIENT")) {
                System.out.println("CLIENT detected in filter!");
                //
                userDetails = this.clientDetailsService.loadUserByUsername(username);
            } else {
                System.out.println("Role is weird! doing USER instead");
                userDetails = this.userDetailsService.loadUserByUsername(username);
            }
            if(jwtService.isTokenValid(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);

    }
    private void sendError(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }
}
