package com.example.jl_entities.config;


import com.example.jl_entities.auth.provider.AgentAuthenticationProvider;
import com.example.jl_entities.auth.provider.ClientAuthenticationProvider;
import com.example.jl_entities.userservice.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private  JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final AgentAuthenticationProvider agentAuthenticationProvider;
    private final ClientAuthenticationProvider clientAuthenticationProvider;
//    private final AccessDeniedHandler accessDeniedHandler;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .authenticationProvider(authenticationProvider)
                .authenticationProvider(agentAuthenticationProvider)
                .authenticationProvider(clientAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    // Custom AccessDeniedHandler implementation
    private static class CustomAccessDeniedHandler implements AccessDeniedHandler {
        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            System.out.println("Access denied!!!");
            response.getWriter().write("Access Denied");
        }
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        System.out.println(" security Filter chain");
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/agent/auth/**").permitAll()
                .requestMatchers("/client/auth/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/load-data").permitAll()
                .requestMatchers("/agent-protected").hasAnyAuthority(Role.ADMIN.name(),Role.AGENT.name())
                .requestMatchers("/client-protected").hasAnyAuthority(Role.ADMIN.name(),Role.CLIENT.name())
                .requestMatchers("/clients").hasAnyAuthority(Role.ADMIN.name(),Role.AGENT.name())
                .requestMatchers("/creanciers").hasAnyAuthority(Role.CLIENT.name())
                .requestMatchers("/admin").hasAuthority(Role.ADMIN.name())
//                .requestMatchers("/impaye").permitAll()
                .anyRequest()
                .permitAll()
//                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return  http.build();
    }
}
