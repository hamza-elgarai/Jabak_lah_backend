package com.example.jl_entities.controller;

import com.example.jl_entities.CredentialsRequest;
import com.example.jl_entities.entity.Client;
import com.example.jl_entities.entity.Creance;
import com.example.jl_entities.entity.Creancier;
import com.example.jl_entities.entity.Impaye;
import com.example.jl_entities.repository.ClientRepository;
import com.example.jl_entities.repository.CreancierRepository;
import com.example.jl_entities.service.FakeDataLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
@ComponentScan(basePackageClasses = FakeDataLoadService.class)
public class PaiementController {
    @Autowired
    private FakeDataLoadService fakeDataLoadService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CreancierRepository creancierRepository;

    @GetMapping("/load-data")
    private String loadData(){
        fakeDataLoadService.loadData();
        return "Successfully loaded data!";
    }
    @PostMapping("/api/client")
    private Client getClient(@RequestBody String tel){
        System.out.println("getting the client");
        Client client=clientRepository.findByTel(tel).orElse(null);
        System.out.println("printing the client "+client);
        return client;
    }
    @GetMapping("/clients")
    private List<Client> getAllClients(){
        return clientRepository.findAll();
    }
    @GetMapping("/creanciers")
    private List<Creancier> getAllCreanciers(){
        return creancierRepository.findAll();
    }
    @GetMapping("/agent-protected")
    private Map<String,String> agent(){
        Map<String,String> resp = new HashMap<>();
        resp.put("message","This is an endpoint only for agents");
        return resp;
    }
    @GetMapping("/client-protected")
    private String client(){
        return "This is an endpoint only for clients!";
    }
    @GetMapping("/impaye")
    private List<Impaye> getImpayes(@RequestBody CredentialsRequest request){
        return fakeDataLoadService.loadImpaye(request);
    }

}
