package com.example.jl_entities.auth.controller;

import com.example.jl_entities.auth.service.AuthService;
import com.example.jl_entities.auth.bodies.authentication.AuthenticationRequest;
import com.example.jl_entities.auth.bodies.authentication.AuthenticationResponse;
import com.example.jl_entities.auth.bodies.authentication.RefreshTokenRequest;
import com.example.jl_entities.auth.bodies.register.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000","https://6481213b9f815c007cfc023a--aquamarine-sprinkles-1893c1.netlify.app/"})
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserAuthController {
    @Autowired
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserRegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity register(
            @RequestBody AuthenticationRequest request
    ){
        AuthenticationResponse response = service.authenticate(request);
        if(response==null) return ResponseEntity.status(401).body("User not found");
        if(response==null) return ResponseEntity.status(401).body("User not found");
        return ResponseEntity.ok(response);
    }
    @PostMapping("/refresh")
    public ResponseEntity<Map> getFromRefreshToken(@RequestBody RefreshTokenRequest request){
        Map<String,String> map = new HashMap<>();
        map.put("token",service.getFromRefreshToken(request.getToken(),"ADMIN"));
        return ResponseEntity.ok(map);
    }


}
