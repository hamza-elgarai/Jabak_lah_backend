package com.example.jl_entities.endpoint;

import com.example.jl_entities.entity.Creancier;
import com.example.jl_entities.entity.Creance;
import com.example.jl_entities.service.CreancierService;
import com.jl_entities.creancierservice.GetAllCreanciersRequest;
import com.jl_entities.creancierservice.GetAllCreanciersResponse;
import com.jl_entities.creancierservice.GetCreancesByCreancierIDRequest;
import com.jl_entities.creancierservice.GetCreancesByCreancierIDResponse;
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

        List<com.jl_entities.creancierservice.Creancier> creancierList = new ArrayList<>();
        for (Creancier creancier : creanciers) {
            com.jl_entities.creancierservice.Creancier creancierS = new com.jl_entities.creancierservice.Creancier();
            BeanUtils.copyProperties(creancier, creancierS);
            creancierList.add(creancierS);
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
        List<com.jl_entities.creancierservice.Creance> creanceList = new ArrayList<>();
        for (Creance creance : creances) {
            com.jl_entities.creancierservice.Creance creanceS = new com.jl_entities.creancierservice.Creance();
            BeanUtils.copyProperties(creance, creanceS);
            creanceList.add(creanceS);
        }
        response.getCreances().addAll(creanceList);

        return response;
    }
}
