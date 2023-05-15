package controller;

import com.example.jl_entities.CredentialsRequest;
import com.example.jl_entities.entity.Creance;
import com.example.jl_entities.entity.Impaye;
import com.example.jl_entities.service.FakeDataLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    @GetMapping("/getClient")
    private List<Impaye> loadClient(){
        return null;
    }
    @GetMapping("/impaye")
    private List<Impaye> getImpayes(@RequestBody CredentialsRequest request){
        return fakeDataLoadService.loadImpaye(request);
    }

}
