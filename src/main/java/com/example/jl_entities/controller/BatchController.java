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

    @GetMapping("/interval/{i}")
    private String setInterval(@PathVariable int i){
        batchConfig.setInterval(i*1000);
        return "Changed interval to "+i+" seconds";

    }
}
