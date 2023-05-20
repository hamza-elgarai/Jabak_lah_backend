package com.example.jl_entities.auth.clientauth;

import com.example.jl_entities.auth.clientauth.ClientAuthService;
import com.example.jl_entities.auth.clientauth.ClientAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
@RequiredArgsConstructor
public class ClientAuthController {


    @Autowired
    private final ClientAuthService service;

    @PostMapping("/register")
    public ResponseEntity<ClientAuthenticationResponse> register(
            @RequestBody ClientRegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ClientAuthenticationResponse> register(
            @RequestBody ClientAuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
