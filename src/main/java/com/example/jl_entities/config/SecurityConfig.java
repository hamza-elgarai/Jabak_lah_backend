package com.example.jl_entities.config;


import com.example.jl_entities.auth.provider.AgentAuthenticationProvider;
import com.example.jl_entities.auth.provider.ClientAuthenticationProvider;
import com.example.jl_entities.userservice.Role;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private  JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final AgentAuthenticationProvider agentAuthenticationProvider;
    private final ClientAuthenticationProvider clientAuthenticationProvider;

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
                .requestMatchers("/admin").hasAuthority(Role.ADMIN.name())
                .requestMatchers("/impaye").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return  http.build();
    }
}
