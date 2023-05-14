package controller;

import com.example.jl_entities.entity.Client;
import com.example.jl_entities.entity.Paiement;
import com.example.jl_entities.repository.PaiementRepository;
import com.example.jl_entities.service.FakeDataLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@ComponentScan(basePackageClasses = FakeDataLoadService.class)
public class PaiementController {
    @Autowired
    private FakeDataLoadService fakeDataLoadService;

    @GetMapping("/load-data")
    private String loadData(){
        fakeDataLoadService.loadData();
        return "Successfully loaded data!";
    }

}
