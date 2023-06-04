package com.example.jl_entities;

import jakarta.xml.ws.WebServiceException;

public class CreanceNotFoundException extends Exception {
    public CreanceNotFoundException(String message){
        super(message);
    }
}
