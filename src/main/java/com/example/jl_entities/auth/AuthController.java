package com.example.jl_entities.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }
    @GetMapping("/refresh")
    public ResponseEntity<Map> getFromRefreshToken(@RequestBody RefreshTokenRequest request){
        Map<String,String> map = new HashMap<>();
        map.put("token",service.getFromRefreshToken(request.getToken()));
        return ResponseEntity.ok(map);
    }


}
