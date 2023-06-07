package com.example.jl_entities.auth.service;


import com.example.jl_entities.auth.bodies.authentication.AuthenticationRequest;
import com.example.jl_entities.auth.bodies.register.AgentRegisterRequest;
import com.example.jl_entities.auth.bodies.authentication.AuthenticationResponse;
import com.example.jl_entities.config.JwtService;
import com.example.jl_entities.entity.Agency;
import com.example.jl_entities.entity.Agent;
import com.example.jl_entities.randomizer.Randomizer;
import com.example.jl_entities.repository.AgencyRepository;
import com.example.jl_entities.repository.AgentRepository;
import com.example.jl_entities.userservice.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AgentAuthService {

    private final AgentRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationProvider agentAuthProvider;
    @Autowired
    private AgencyRepository agencyRepository;

    public Map<String,String> register(AgentRegisterRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        Agency agency = agencyRepository.findById(request.getAgencyId()).orElse(null);
        try {
            date = sdf.parse(request.getBirthday());
        } catch (ParseException e) {
            System.out.println("Date parse exception");
            return Map.of("message","Date n'est pas valable");
        }

        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        String telRegex = "^0\\d{9}$";
        if(!request.getTel().matches(telRegex)){
            return Map.of("message","Le téléphone doit être de format 0XXXXXXXXX");
        }
        if(!request.getEmail().matches(emailRegex)){
            return Map.of("message","L'email est invalide");
        }


        var user = Agent.builder()
                .fname(request.getFname())
                .lname(request.getLname())
                .identityType(request.getIdentityType())
                .identityNumber(request.getIdentityNumber())
                .username(Randomizer.generateAgentUser())
                .birthday(date)
                .address(request.getAddress())
                .tel(request.getTel())
                .email(request.getEmail())
                .password(passwordEncoder.encode(Randomizer.generatePassword()))
                .isPasswordChanged(false)
                .immatriculation(request.getImmatriculation())
                .licenseNumber(request.getLicenseNumber())
                .idFilePath(request.getIdFilePath())
                .role(Role.AGENT)
                .agency(agency)
                .build();
        repository.saveAndFlush(user);
//        var jwtToken = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
        return Map.of("message","Agent ajouté!");
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("authenticate!!!");
        try{
            agentAuthProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )

            );
        }catch (AuthenticationException e){
            return null;
        }
        Agent user = repository.findByUsername(request.getUsername())
                .orElse(null);
        System.out.println("Agent found");
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
    public Map<String,String> changePassword(@RequestBody AuthenticationRequest request){
        Agent agent = repository.findByUsername(request.getUsername()).orElse(null);
        if(agent==null) return Map.of("message","Agent non trouvé");

        if(request.getPassword().length()<8) return Map.of("message","Le mot de passe doit contenir 8 caractères ou plus");

        agent.setPassword(passwordEncoder.encode(request.getPassword()));
        agent.setIsPasswordChanged(true);
        repository.saveAndFlush(agent);

//        var jwtToken = jwtService.generateToken(agent);
//        var refreshToken = jwtService.generateRefreshToken(agent);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
        return Map.of("message","Mot de passe changé");
    }
    public Boolean isPasswordChanged( String username){
        Agent agent= repository.findByUsername(username).orElse(null);
        if(agent==null) return null;
        return agent.getIsPasswordChanged();
    }
}
