package com.example.jl_entities;

//import com.example.jl_entities.controller.PaiementController;
import com.example.jl_entities.entity.Agency;
import com.example.jl_entities.entity.Paiement;
import com.example.jl_entities.repository.PaiementRepository;
import com.example.jl_entities.service.PaiementService;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableScheduling
public class JlEntitiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(JlEntitiesApplication.class, args);
    }



}
