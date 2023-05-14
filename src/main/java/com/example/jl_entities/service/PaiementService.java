package com.example.jl_entities.service;

import com.example.jl_entities.entity.Client;
import com.example.jl_entities.entity.Paiement;
import com.example.jl_entities.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementService {
    @Autowired
    private PaiementRepository paiementRepository;

//    List<Paiement> getPaiementsByClient(Long clientId){
//        return paiementRepository.getPaiementsByClientId(clientId);
//    }
}
