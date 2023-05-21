package com.example.jl_entities.auth.service;

import com.example.jl_entities.auth.bodies.authentication.AuthenticationRequest;
import com.example.jl_entities.auth.bodies.authentication.AuthenticationResponse;
import com.example.jl_entities.auth.bodies.register.ClientRegisterRequest;
import com.example.jl_entities.config.JwtService;
import com.example.jl_entities.entity.AccountType;
import com.example.jl_entities.entity.Client;
import com.example.jl_entities.entity.CompteBancaire;
import com.example.jl_entities.randomizer.Randomizer;
import com.example.jl_entities.repository.AccountTypeRepository;
import com.example.jl_entities.repository.ClientRepository;
import com.example.jl_entities.repository.CompteBancaireRepository;
import com.example.jl_entities.userservice.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ClientAuthService {


    private final ClientRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationProvider clientAuthProvider;
    @Autowired
    private AccountTypeRepository accountTypeRepository;
    @Autowired
    private CompteBancaireRepository compteBancaireRepository;


    public AuthenticationResponse register(ClientRegisterRequest request) {

        AccountType accountType = accountTypeRepository.findById(request.getIdType()).orElse(null);
        Double plafond;
        if (accountType != null) {
            plafond = accountType.getPlafond();
        } else {
            System.out.println("plafond get problem !!!");
            return null;
        }

        CompteBancaire compteBancaire = new CompteBancaire(null, Randomizer.generateClientCompte(), plafond);
        compteBancaireRepository.saveAndFlush(compteBancaire);

        var user = Client.builder()
                .fname(request.getFname())
                .lname(request.getLname())
                .email(request.getEmail())
                .compteBancaire(compteBancaire)
                .verificationStatus("pending")
                .tel(request.getTel())
                .type(accountType)
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CLIENT)
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
            clientAuthProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )

            );
        }catch(AuthenticationException exception){
            return null;
        }
        Client user = repository.findByTel(request.getUsername())
                .orElse(null);
        System.out.println("Client found");
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

}
