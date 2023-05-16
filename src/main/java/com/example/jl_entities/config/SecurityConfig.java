package com.example.jl_entities.config;


import com.example.jl_entities.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        System.out.println(" security Filter chain");
        http
//                .cors()
//                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**")
                .permitAll()
                .requestMatchers("/load-data").permitAll()
                .requestMatchers("/impaye").permitAll()
                .requestMatchers("/getClient").permitAll()
                .requestMatchers("/admin").hasAuthority(Role.ADMIN.name())
                .requestMatchers("/impaye").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return  http.build();
    }
}
