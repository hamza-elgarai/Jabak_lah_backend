package com.example.jl_entities.service;

import com.example.jl_entities.bodies.ConfirmerPayementBody;
import com.example.jl_entities.entity.*;
import com.example.jl_entities.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PaiementService {
    @Autowired
    private PaiementRepository paiementRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ImpayeRepository impayeRepository;
    @Autowired
    private CompteBancaireRepository compteBancaireRepository;
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;



    public Map<String,String> confirmerPayement(ConfirmerPayementBody request){

        //Check client
        Client client = clientRepository.findById(request.getClientId()).orElse(null);
        if(client==null) return Map.of("message","Can't find client");
        //Check compte
        CompteBancaire compteBancaire = client.getCompteBancaire();
        if(compteBancaire==null) return Map.of("message","Can't find compte");
        //Check verification code
        List<VerificationCode> codeList = verificationCodeRepository.findByClientId(request.getClientId());
        if(codeList.size()==0){
            return Map.of("message","Le code est erroné");
        }
        if(!codeList.get(0).getClient().getId().equals(request.getClientId())){
            return Map.of("message","Le code de vérification est erroné");
        }
        if(!codeList.get(0).getCode().equals(request.getCode())){
            System.out.println(request.getCode());
            System.out.println(codeList.get(0).getCode());
            return Map.of("message","Code est erroné");
        }


        //get impayes
        List<Impaye> impayes = new ArrayList<>();
        for (Long id: request.getImpayes()){
            Impaye impaye = impayeRepository.findById(id).orElse(null);
            if(impaye==null) continue;
            if(impaye.getIsPaid()) continue;
            impayes.add(impaye);
        }

        //Check if solde is enough
        Double sum = 0.0;
        for(Impaye impaye:impayes){
            sum+= impaye.getPrice();
        }
        if(sum>compteBancaire.getSolde()) return Map.of("message","Solde insuffisant");

        //Everything is set, we start paying each impaye
        String numeroCompte = compteBancaire.getNumeroCompte();
        try {

            for (Impaye impaye : impayes) {

                //Register paiement for client and set impaye.isPaid to true
                Paiement paiement = new Paiement(null, new Date().getTime(), numeroCompte, client, impaye);
                impaye.setIsPaid(true);
                client.getPaiements().add(paiement);
                paiementRepository.saveAndFlush(paiement);
                clientRepository.saveAndFlush(client);
                impayeRepository.saveAndFlush(impaye);

                //Register operation for compteBancaire (hirtory of transactions)
                Operation operation = new Operation(null, "paiement", impaye.getName(), new Date().getTime(), impaye.getPrice(), compteBancaire);
                compteBancaire.getOperations().add(operation);
                operationRepository.saveAndFlush(operation);

            }
            compteBancaire.setSolde(compteBancaire.getSolde() - sum);
            compteBancaireRepository.save(compteBancaire);
            compteBancaireRepository.flush();

        }catch(Exception e){
            e.printStackTrace();
            return Map.of("message","SQL error");
        }
        return Map.of("message","Paiement effectué");


    }

//    List<Paiement> getPaiementsByClient(Long clientId){
//        return paiementRepository.getPaiementsByClientId(clientId);
//    }
}
