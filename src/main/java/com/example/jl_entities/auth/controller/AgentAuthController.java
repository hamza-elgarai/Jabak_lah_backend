package com.example.jl_entities.auth.controller;

import com.example.jl_entities.auth.bodies.authentication.IsPasswordChangedRequest;
import com.example.jl_entities.auth.bodies.authentication.RefreshTokenRequest;
import com.example.jl_entities.auth.service.AgentAuthService;
import com.example.jl_entities.auth.bodies.authentication.AuthenticationRequest;
import com.example.jl_entities.auth.bodies.register.AgentRegisterRequest;
import com.example.jl_entities.auth.bodies.authentication.AuthenticationResponse;
import com.example.jl_entities.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/agent/auth")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
//@RequiredArgsConstructor
public class AgentAuthController {
    private final AgentAuthService service;
    private final AuthService authService;

    @Autowired
    public AgentAuthController(AgentAuthService service, AuthService authService) {
        this.service = service;
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AgentRegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity register(
            @RequestBody AuthenticationRequest request
    ){
        AuthenticationResponse response = service.authenticate(request);
        if(response==null) {
            Map<String,String> error = new HashMap<>();
            error.put("error","Agent not found");
            return ResponseEntity.status(401).contentType(MediaType.APPLICATION_JSON).body(error);
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/refresh")
    public ResponseEntity<Map> getFromRefreshToken(@RequestBody RefreshTokenRequest request){
        Map<String,String> map = new HashMap<>();
        map.put("token",authService.getFromRefreshToken(request.getToken(),"AGENT"));
        return ResponseEntity.ok(map);
    }
    @PostMapping("/change-password")
    @PreAuthorize("isAuthenticated() and #request.username == authentication.principal.username")
    public ResponseEntity<AuthenticationResponse> changePassword(@RequestBody AuthenticationRequest request){
        AuthenticationResponse response=null;
        if(request.getPassword().equals("") || request.getPassword().length()<8 ){
            return null;
        }
        response = service.changePassword(request);
        if(response==null) return null;
        return ResponseEntity.ok(response);
    }
    @PostMapping("/is-password-changed")
    @PreAuthorize("isAuthenticated() and #request.username == authentication.principal.username")
    public ResponseEntity<Boolean> isPasswordChanged(@RequestBody IsPasswordChangedRequest request){
        return ResponseEntity.ok(service.isPasswordChanged(request.getUsername()));
    }


}
