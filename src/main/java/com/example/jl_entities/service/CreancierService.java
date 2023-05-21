package com.example.jl_entities.service;

import com.example.jl_entities.entity.Creance;
import com.example.jl_entities.entity.Creancier;
import com.example.jl_entities.repository.CreanceRepository;
import com.example.jl_entities.repository.CreancierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CreancierService {
    @Autowired
    private final CreancierRepository creancierRepository;
    @Autowired
    private final CreanceRepository creanceRepository;


    public CreancierService(CreancierRepository creancierRepository, CreanceRepository creanceRepository) {
        this.creancierRepository = creancierRepository;
        this.creanceRepository = creanceRepository;
    }



    /*public Creancier createCreancier(String name, String address, String phoneNumber) {
        Creancier creancier = new Creancier(name, address, phoneNumber);
        return creancierRepository.save(creancier);
    }*/

    /*public void updateCreancier(Creancier creancier, String name, String address, String phoneNumber) {
        creancier.setName(name);
        creancier.setAddress(address);
        creancier.setPhoneNumber(phoneNumber);
        creancierRepository.save(creancier);
    }*/

    public void deleteCreancier(Creancier creancier) {
        creancierRepository.delete(creancier);
    }

    public Creancier getCreancierById(long id) {
        return creancierRepository.findById(id).orElse(null);
    }

    public List<Creancier> getAllCreanciers() {
        return  creancierRepository.findAll();
    }

    public List<Creance> getCreancesByCreancierID(long id) {return creanceRepository.findAllByCreancierId(id);}
}

    /*public List<Creancier> searchCreanciers(String keyword) {
        return creancierRepository.findByNameContaining(keyword);
    }*/

