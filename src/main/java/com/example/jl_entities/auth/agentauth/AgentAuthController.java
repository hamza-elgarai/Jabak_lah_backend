package com.example.jl_entities.auth.agentauth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agent/auth")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
@RequiredArgsConstructor
public class AgentAuthController {
    @Autowired
    private final AgentAuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AgentRegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AgentAuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }
    @GetMapping("/test")
    public String hello(){
        return "Hello";
    }


}
