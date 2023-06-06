package com.example.jl_entities.auth.controller;

import com.example.jl_entities.auth.bodies.authentication.AuthenticationRequest;
import com.example.jl_entities.auth.bodies.authentication.AuthenticationResponse;
import com.example.jl_entities.auth.bodies.authentication.IsPasswordChangedRequest;
import com.example.jl_entities.auth.bodies.authentication.RefreshTokenRequest;
import com.example.jl_entities.auth.bodies.register.ClientRegisterRequest;
import com.example.jl_entities.auth.service.AuthService;
import com.example.jl_entities.auth.service.ClientAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/client/auth")
@CrossOrigin(origins = {"https://hostingbranch--effulgent-dieffenbachia-045a91.netlify.app","http://localhost:4200", "http://localhost:3000"})
@RequiredArgsConstructor
public class ClientAuthController {


    @Autowired
    private final ClientAuthService service;
    @Autowired
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody ClientRegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity register(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse response = service.authenticate(request);
        if(response==null) {
            Map<String,String> error = new HashMap<>();
            error.put("error","Client not found");
            return ResponseEntity.status(401).contentType(MediaType.APPLICATION_JSON).body(error);
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/refresh")
    public ResponseEntity<Map> getFromRefreshToken(@RequestBody RefreshTokenRequest request){
        Map<String,String> map = new HashMap<>();
        map.put("token",authService.getFromRefreshToken(request.getToken(),"CLIENT"));
        return ResponseEntity.ok(map);
    }

    @PostMapping("/change-password")
    @PreAuthorize("isAuthenticated() and #request.username == authentication.principal.username")
    public ResponseEntity<AuthenticationResponse> changePassword(@RequestBody AuthenticationRequest request){
        AuthenticationResponse response;
        if(request.getPassword().equals("") || request.getPassword().length()<8 ){
            return null;
        }
        response = service.changePassword(request);
        if(response==null) return null;
        return ResponseEntity.ok(response);
    }
    @PostMapping("/is-password-changed")
    @PreAuthorize("isAuthenticated() and #request.username == authentication.principal.username")
    public ResponseEntity<Boolean> isPasswordChanged(@RequestBody IsPasswordChangedRequest request) {
        return ResponseEntity.ok(service.isPasswordChanged(request.getUsername()));
    }
}
