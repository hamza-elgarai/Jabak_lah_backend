package com.example.jl_entities.controller;

import com.example.jl_entities.CreanceNotFoundException;
import com.example.jl_entities.CredentialsRequest;
import com.example.jl_entities.bodies.EditClientRequest;
import com.example.jl_entities.entity.*;
import com.example.jl_entities.repository.*;
import com.example.jl_entities.service.FakeDataLoadService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

@RestController
@RequestMapping("/")
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000","https://6481213b9f815c007cfc023a--aquamarine-sprinkles-1893c1.netlify.app/"})
@ComponentScan(basePackageClasses = FakeDataLoadService.class)
public class PaiementController {
    @Autowired
    private FakeDataLoadService fakeDataLoadService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CreancierRepository creancierRepository;
    @Autowired
    private AgencyRepository agencyRepository;
    @Autowired
    private AccountTypeRepository accountTypeRepository;
    @Autowired
    private CompteBancaireRepository compteBancaireRepository;
    @Autowired
    private OperationRepository operationRepository;

    @GetMapping("/load-data")
    private String loadData(){
        fakeDataLoadService.loadData();
        return "Successfully loaded data!";
    }

    @DeleteMapping("/client/{id}")
    private ResponseEntity<Boolean> deleteClient(@PathVariable Long id){
        Client c = clientRepository.findById(id).orElse(null);
        if(c==null) return ResponseEntity.ok(false);
        clientRepository.deleteById(id);
        return ResponseEntity.ok(true);
    }
    @PatchMapping("/client/{id}")
    private ResponseEntity<Map> editClient(@PathVariable Long id,@RequestBody EditClientRequest request){
        Client c = clientRepository.findById(id).orElse(null);
        if(c==null) return ResponseEntity.status(404).body(Map.of("message","Client non trouvé"));
//        AccountType accountType = accountTypeRepository.findById(request.getIdType()).orElse(null);
//        if(accountType==null) return ResponseEntity.status(404).body(Map.of("message","Type id not valid"));
        c.setFname(request.getFname());c.setLname(request.getLname());c.setEmail(request.getEmail());c.setTel(request.getTel());
        System.out.println(c.getTel());
        try{
            clientRepository.save(c);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("message","Le numéro de téléphone existe déjà"));
        }
        return ResponseEntity.ok(Map.of("message","Client modifié"));
    }
    @PatchMapping("/client/addSolde/{id}")
    private ResponseEntity<Map> addSolde(@PathVariable Long id, @RequestBody Map<String,Double> body){
        Client c = clientRepository.findById(id).orElse(null);
        if(c==null) return ResponseEntity.status(404).body(Map.of("message","Client non trouvé"));

        CompteBancaire compteBancaire = c.getCompteBancaire();
        AccountType accountType = c.getType();

        Double versement = body.get("versement");
        if(versement==null) ResponseEntity.status(400).body(Map.of("message","Le versement n'est pas envoyé"));

        Double newSolde = compteBancaire.getSolde()+body.get("versement");
        if(newSolde>accountType.getPlafond()){
            return ResponseEntity.status(500).body(Map.of("message","Echec! Dépassement du plafond"));
        }

        compteBancaire.setSolde(newSolde);
        compteBancaireRepository.saveAndFlush(compteBancaire);

        Operation op = new Operation(null,"versement","Versement de l'agent", new Date().getTime(),versement,compteBancaire);
        operationRepository.saveAndFlush(op);

        return ResponseEntity.ok(Map.of("message","Versement ajouté de "+versement+" DH"));
    }
    @GetMapping("/client/{id}")
    private Client getClient(@PathVariable("id") Long id){
        return clientRepository.findById(id).orElse(null);
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
    private ResponseEntity<List<Impaye>> getImpayes(@RequestBody CredentialsRequest request){
        try {
            return ResponseEntity.ok(fakeDataLoadService.loadImpaye(request));
        }catch(CreanceNotFoundException exception){
            return ResponseEntity.status(404).body(new ArrayList<>());
        }
    }
    @GetMapping("/agencies")
    private List<Agency> getAgencies(){
        return agencyRepository.findAll();
    }

}
