package com.example.jl_entities.auth.service;

import com.example.jl_entities.auth.bodies.authentication.AuthenticationRequest;
import com.example.jl_entities.auth.bodies.authentication.AuthenticationResponse;
import com.example.jl_entities.auth.bodies.register.ClientRegisterRequest;
import com.example.jl_entities.config.JwtService;
import com.example.jl_entities.entity.AccountType;
import com.example.jl_entities.entity.Agent;
import com.example.jl_entities.entity.Client;
import com.example.jl_entities.entity.CompteBancaire;
import com.example.jl_entities.randomizer.Randomizer;
import com.example.jl_entities.repository.AccountTypeRepository;
import com.example.jl_entities.repository.ClientRepository;
import com.example.jl_entities.repository.CompteBancaireRepository;
import com.example.jl_entities.userservice.Role;
import com.mysql.cj.jdbc.exceptions.SQLError;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;


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


    public Map<String,String> register(ClientRegisterRequest request) {
        Client current = repository.findByTel(request.getTel()).orElse(null);
        if(current!=null) return Map.of("message","Numéro de téléphone existe déjà");

        if(request.getSolde()<200) return Map.of("message","Le solde minimum est 200DH");

        AccountType accountType = accountTypeRepository.findById(request.getIdType()).orElse(null);

        if(accountType==null) return Map.of("message","Erreur de type");

        if(request.getSolde()>accountType.getPlafond())
            return Map.of("message","Le solde inséré dépasse le plafond");


        CompteBancaire compteBancaire = new CompteBancaire(null, Randomizer.generateClientCompte(), request.getSolde());

        var user = Client.builder()
                .fname(request.getFname())
                .lname(request.getLname())
                .email(request.getEmail())
                .compteBancaire(compteBancaire)
                .verificationStatus("pending")
                .tel(request.getTel())
                .type(accountType)
                .password(passwordEncoder.encode(Randomizer.generatePassword()))
                .isPasswordChanged(false)
                .role(Role.CLIENT)
                .build();
        compteBancaireRepository.saveAndFlush(compteBancaire);
        repository.saveAndFlush(user);
        return Map.of("message","Client créé");

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

    public AuthenticationResponse changePassword(@RequestBody AuthenticationRequest request){
        Client client = repository.findByTel(request.getUsername()).orElse(null);
        if(client==null) return null;
        client.setPassword(passwordEncoder.encode(request.getPassword()));
        client.setIsPasswordChanged(true);
        repository.saveAndFlush(client);

        var jwtToken = jwtService.generateToken(client);
        var refreshToken = jwtService.generateRefreshToken(client);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
    public Boolean isPasswordChanged( String username){
        Client client= repository.findByTel(username).orElse(null);
        if(client==null) return null;
        return client.getIsPasswordChanged();
    }
}
