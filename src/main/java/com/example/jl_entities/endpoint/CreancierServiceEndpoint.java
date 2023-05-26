package com.example.jl_entities.endpoint;


import com.example.jl_entities.CredentialsRequest;
import com.example.jl_entities.entity.*;
import com.example.jl_entities.service.CreancierService;
import com.jl_entities.creancierservice.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Endpoint
public class CreancierServiceEndpoint {
    private static final String NAMESPACE_URI = "http://www.jl_entities.com/creancierservice";

    @Autowired
    private CreancierService creancierService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllCreanciersRequest")
    @ResponsePayload
    public GetAllCreanciersResponse getAllCreanciers(@RequestPayload GetAllCreanciersRequest request) {
        GetAllCreanciersResponse response = new GetAllCreanciersResponse();

        List<Creancier> creanciers = creancierService.getAllCreanciers();

        List<CreancierSoap> creancierList = new ArrayList<>();
        for (Creancier creancier : creanciers) {
            CreancierSoap creancierSoap = new CreancierSoap();
            BeanUtils.copyProperties(creancier, creancierSoap);
            creancierList.add(creancierSoap);
        }
        response.getCreanciers().addAll(creancierList);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCreancesByCreancierIDRequest")
    @ResponsePayload
    public GetCreancesByCreancierIDResponse getCreancesByCreancierID(@RequestPayload GetCreancesByCreancierIDRequest request) {
        GetCreancesByCreancierIDResponse response = new GetCreancesByCreancierIDResponse();

        long creancierID = request.getId();
        List<Creance> creances = creancierService.getCreancesByCreancierID(creancierID);
        List<CreanceSoap> creanceList = new ArrayList<>();
        for (Creance creance : creances) {
            CreanceSoap creanceSoap = new CreanceSoap();
            BeanUtils.copyProperties(creance, creanceSoap);
            creanceList.add(creanceSoap);
        }
        response.getCreances().addAll(creanceList);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetImpayesByCreanceIDRequest")
    @ResponsePayload
    public GetImpayesByCreanceIDResponse getImpayesByCreanceID(@RequestPayload GetImpayesByCreanceIDRequest request){
        GetImpayesByCreanceIDResponse response = new GetImpayesByCreanceIDResponse();

        CredentialsRequest credentialsRequest=new CredentialsRequest();
        credentialsRequest.setCreanceId(request.getCreanceId());

        Map<String,String> credentialsMap=new HashMap<String,String>();
        for(Credential credential: request.getCredentials().getCredential()){
//            System.out.println(credential.getCredentialName()+" "+credential.getCredentialValue());
            credentialsMap.put(credential.getCredentialName(),credential.getCredentialValue());
        }
        credentialsRequest.setCredentials(credentialsMap);

        List<Impaye> impayes = creancierService.getImpayesByCreanceID(credentialsRequest);

        List<ImpayeSoap> impayeSoapList = new ArrayList<>();
        for (Impaye impaye : impayes){
            ImpayeSoap impayeSoap = new ImpayeSoap();
            BeanUtils.copyProperties(impaye,impayeSoap);
            impayeSoapList.add(impayeSoap);
        }
        response.getImpayes().addAll(impayeSoapList);
        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetFormsByCreanceIDRequest")
    @ResponsePayload
    private GetFormsByCreanceIDResponse getFormsByCreanceID(@RequestPayload GetFormsByCreanceIDRequest request){
        GetFormsByCreanceIDResponse response = new GetFormsByCreanceIDResponse();
        List<Formulaire> formulaires = creancierService.getFormsByCreanceID(request.getId());
        List<Champ> champs = creancierService.getChampsByFormulaireID(request.getId());

        List<FormsSoap> formsSoapList = new ArrayList<>();
        for (Formulaire formulaire: formulaires) {
            FormsSoap formsSoap = new FormsSoap();
            BeanUtils.copyProperties(formulaire, formsSoap);

            // Copy the champs list manually
            List<ChampSoap> champsList = new ArrayList<>();
            for (Champ champ : champs) {
                ChampSoap champSoap = new ChampSoap();
                BeanUtils.copyProperties(champ,champSoap );
                champsList.add(champSoap);
            }
            formsSoap.getChamps().addAll(champsList);
            formsSoapList.add(formsSoap);
        }
        /*for (Formulaire formulaire:formulaires){
            FormsSoap formsSoap = new FormsSoap();
            BeanUtils.copyProperties(formulaire,formsSoap);
            List<Champ> champsList = new ArrayList<>();
            for (Champ champ : formulaire.getChamps()) {
                Champ champCopy = new Champ();
                BeanUtils.copyProperties(champ, champCopy);
                champsList.add(champCopy);
            }
            formsSoap.setChamps(champsList);

            formsSoapList.add(formsSoap);
        }*/
        response.getForms().addAll(formsSoapList);
        System.out.println(response.getForms());
        return response;
    }
}
