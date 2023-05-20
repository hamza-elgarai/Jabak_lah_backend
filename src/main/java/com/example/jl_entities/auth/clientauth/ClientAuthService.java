package com.example.jl_entities.auth.clientauth;

import com.example.jl_entities.auth.clientauth.ClientAuthenticationResponse;
import com.example.jl_entities.config.JwtService;
import com.example.jl_entities.entity.AccountType;
import com.example.jl_entities.entity.Agency;
import com.example.jl_entities.entity.Client;
import com.example.jl_entities.entity.CompteBancaire;
import com.example.jl_entities.randomizer.Randomizer;
import com.example.jl_entities.repository.AccountTypeRepository;
import com.example.jl_entities.repository.AgencyRepository;
import com.example.jl_entities.repository.ClientRepository;
import com.example.jl_entities.repository.CompteBancaireRepository;
import com.example.jl_entities.userservice.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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


    public ClientAuthenticationResponse register(ClientRegisterRequest request) {

        AccountType accountType = accountTypeRepository.findById(request.getIdType()).orElse(null);
        Double plafond;
        if (accountType != null) {
            plafond = accountType.getPlafond();
        } else {
            System.out.println("plafond get problem !!!");
            return null;
        }

        CompteBancaire compteBancaire = new CompteBancaire(null, Randomizer.generateClientCompte(), plafond);

        var user = Client.builder()
                .fname(request.getFname())
                .lname(request.getLname())
                .email(request.getEmail())
                .compteBancaire(compteBancaire)
                .verificationStatus("pending")
                .tel(request.getTel())
                .id(request.getIdType())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CLIENT)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return ClientAuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public ClientAuthenticationResponse authenticate(ClientAuthenticationRequest request) {
        System.out.println("authenticate!!!");
        clientAuthProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getTel(),
                        request.getPassword()
                )

        );
        Client user = repository.findByTel(request.getTel())
                .orElse(null);
        System.out.println("Client found");
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return ClientAuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
    public String getFromRefreshToken(String accessToken){
        return jwtService.generateFromRefreshToken(accessToken,"CLIENT");
    }
}
