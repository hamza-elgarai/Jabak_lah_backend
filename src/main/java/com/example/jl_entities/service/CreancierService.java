package com.example.jl_entities.service;

import com.example.jl_entities.CredentialsRequest;
import com.example.jl_entities.entity.*;
import com.example.jl_entities.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CreancierService {
    @Autowired
    private final CreancierRepository creancierRepository;
    @Autowired
    private final CreanceRepository creanceRepository;
    @Autowired
    private final ImpayeRepository impayeRepository;
    @Autowired
    private final FormulaireRepository formulaireRepository;
    @Autowired
    private final ChampRepository champRepository;
    @Autowired
    private FakeDataLoadService fakeDataLoadService;


    public CreancierService(CreancierRepository creancierRepository, CreanceRepository creanceRepository, ImpayeRepository impayeRepository, FormulaireRepository formulaireRepository, ChampRepository champRepository) {
        this.creancierRepository = creancierRepository;
        this.creanceRepository = creanceRepository;
        this.impayeRepository = impayeRepository;
        this.formulaireRepository = formulaireRepository;
        this.champRepository = champRepository;
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
    public List<Impaye> getImpayesByCreanceID(CredentialsRequest request) {
        return fakeDataLoadService.loadImpaye(request);
//        return impayeRepository.findAllByCreanceId(id);
    }
    public List<Formulaire> getFormsByCreanceID(long id){
        return formulaireRepository.findAllByCreanceId(id);
    }
    public List<Champ> getChampsByFormulaireID(long id){
        return champRepository.findByFormulaireId(id);
    }
}

    /*public List<Creancier> searchCreanciers(String keyword) {
        return creancierRepository.findByNameContaining(keyword);
    }*/

