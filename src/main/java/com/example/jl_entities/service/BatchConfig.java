package com.example.jl_entities.service;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BatchConfig {
    private volatile int interval=60000;
    private Boolean isOn=false;

}
