package com.example.jl_entities.endpoint;

import com.example.jl_entities.entity.Creancier;
import com.example.jl_entities.entity.Creance;
import com.example.jl_entities.service.CreancierService;
import com.jl_entities.creancierservice.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

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
}
