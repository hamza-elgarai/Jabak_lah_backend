package com.example.jl_entities.auth.service;


import com.example.jl_entities.auth.bodies.authentication.AuthenticationRequest;
import com.example.jl_entities.auth.bodies.authentication.AuthenticationResponse;
import com.example.jl_entities.auth.bodies.register.UserRegisterRequest;
import com.example.jl_entities.config.JwtService;
import com.example.jl_entities.randomizer.Randomizer;
import com.example.jl_entities.userservice.Role;
import com.example.jl_entities.userservice.User;
import com.example.jl_entities.userservice.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;

    public AuthenticationResponse register(UserRegisterRequest request) {
        System.out.println(request);
        Role role = Role.USER ;
        if(request.getRole()!=null && request.getRole().equals("admin")){
            role = Role.ADMIN;
        }
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        repository.saveAndFlush(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("authenticate!!!");
        try{
            authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        }catch (AuthenticationException exception){
            return null;
        }
        User user = repository.findByEmail(request.getUsername())
                .orElseThrow();
        System.out.println("user found");
        Role role = user.getRole();
        if(!role.toString().equals("ADMIN")){
            return null;
        }

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                                .token(jwtToken)
                                .refreshToken(refreshToken)
                                .build();
    }

    public String getFromRefreshToken(String accessToken,String role){
        return jwtService.generateFromRefreshToken(accessToken,role);
    }
}
