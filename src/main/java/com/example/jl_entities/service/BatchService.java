package com.example.jl_entities.service;

import com.example.jl_entities.entity.Client;
import com.example.jl_entities.repository.ClientRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class BatchService {

    private final ClientRepository clientRepository;
    private BatchConfig batchConfig;
    private long i;
    private final List<String> emailBlackList = List.of(
            "ahmedboucetta@gmail.com",
            "laurantblanc10@outlook.fr",
            "moussaben56@gmail.com",
            "bear2333@gmail.com"
    );
    private final List<String> telBlackList = List.of(
            "0623683120",
            "0600001111",
            "0633333333",
            "0700001111"
    );

    @Autowired
    public BatchService(ClientRepository clientRepository,BatchConfig batchConfig){
        this.clientRepository = clientRepository;
        this.batchConfig = batchConfig;
    }

    @Scheduled(fixedDelayString = "#{batchConfig.interval}")
    public void processBatch(){
        if(batchConfig.getIsOn()){
            List<Client> clients = clientRepository.findAllByVerificationStatus("pending");
            for (Client client:clients){
                if(emailBlackList.contains(client.getEmail().toLowerCase()) || telBlackList.contains(client.getTel()) ){
                    client.setVerificationStatus("not-verified");
                }
                else{
                    client.setVerificationStatus("verified");
                }
                clientRepository.saveAndFlush(client);
            }
            System.out.println("Batch process ");

        }
    }

}
