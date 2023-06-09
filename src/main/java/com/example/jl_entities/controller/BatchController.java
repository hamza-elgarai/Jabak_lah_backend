package com.example.jl_entities.controller;

import com.example.jl_entities.service.BatchConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchController {

    private BatchConfig batchConfig;

    @Autowired
    public BatchController(BatchConfig batchConfig){
        this.batchConfig = batchConfig;
    }

    @GetMapping("/toggle/{i}")
    private Boolean setInterval(@PathVariable int i){
        if(i==1) batchConfig.setIsOn(true);
        else batchConfig.setIsOn(false);
        return batchConfig.getIsOn();

    }
}
