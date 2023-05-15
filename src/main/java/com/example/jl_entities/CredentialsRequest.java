package com.example.jl_entities;

import com.example.jl_entities.entity.Creance;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CredentialsRequest {
    private Map<String,String> credentials ;
    private Long creanceId;
}
