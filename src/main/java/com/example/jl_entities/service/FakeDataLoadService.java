package com.example.jl_entities.service;

import com.example.jl_entities.CreanceNotFoundException;
import com.example.jl_entities.CredentialsRequest;
import com.example.jl_entities.auth.service.AuthService;
import com.example.jl_entities.auth.bodies.register.UserRegisterRequest;
import com.example.jl_entities.auth.service.AgentAuthService;
import com.example.jl_entities.auth.bodies.register.AgentRegisterRequest;
import com.example.jl_entities.auth.service.ClientAuthService;
import com.example.jl_entities.auth.bodies.register.ClientRegisterRequest;
import com.example.jl_entities.entity.*;
import com.example.jl_entities.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FakeDataLoadService {
    private AccountTypeRepository accountTypeRepository;
    private AgencyRepository agencyRepository;
    private ChampRepository champRepository;
    private ClientRepository clientRepository;
    private CreanceRepository creanceRepository;
    private CreancierRepository creancierRepository;
    private FormulaireRepository formulaireRepository;
    private ImpayeRepository impayeRepository;
    private PaiementRepository paiementRepository;
    private ImpayeCredentialRepository impayeCredentialRepository;
    private ImpayeRepositoryImpl impayeRepositoryImpl;
    private CompteBancaireRepository compteBancaireRepository;
    private AuthService authService;
    private AgentAuthService agentAuthService;
    private ClientAuthService clientAuthService;


    public List<Impaye> loadImpaye(CredentialsRequest request) throws CreanceNotFoundException {

        return impayeRepositoryImpl.findAllByCredentials(request);
    }
    public void loadData(){

        authService.register(new UserRegisterRequest("Hamza","ELGARAI","hamza@gmail.com","hamza123",null));
        authService.register(new UserRegisterRequest("John","Doe","johndoe@gmail.com","john123","admin"));

        // 1 - Account types
        AccountType ac1 = new AccountType(null,1,200.0);
        AccountType ac2 = new AccountType(null,2,2000.0);
        AccountType ac3 = new AccountType(null,3,50000.0);
        accountTypeRepository.save(ac1);
        accountTypeRepository.save(ac2);
        accountTypeRepository.save(ac3);
        accountTypeRepository.flush();

//        CompteBancaire compteBancaire1 = new CompteBancaire(null,"1234 5678 9101 1123",500.0);
//        CompteBancaire compteBancaire2 = new CompteBancaire(null,"0003 1234 2434 3863",5800.0);
//        CompteBancaire compteBancaire3 = new CompteBancaire(null,"5603 8976 5722 3540",1800.0);

//        compteBancaireRepository.saveAllAndFlush(List.of(
//                compteBancaire1,compteBancaire2,compteBancaire3
//        ));


        // 2 - Clients
        ClientRegisterRequest client1;
        ClientRegisterRequest client2;
        ClientRegisterRequest client3;
        client1 = new ClientRegisterRequest("Hamza","ELGARAI","hamzaelgarai10@gmail.com","0634348550","hamza1234", 1L,200.0);
        client2 = new ClientRegisterRequest("Mohamed","HAMDANI","hamdanimee@gmail.com","0632751035","medqs546", 2L,250.0);
        client3 = new ClientRegisterRequest("Mohamed","BENAARROUCH","medbenarr20@gmail.com","06359820348","medben3544", 3L,500.0);

        clientAuthService.register(client1);
        clientAuthService.register(client2);
        clientAuthService.register(client3);

        // 8 - Formulaires
        Formulaire formulaire1 = new Formulaire(null,new ArrayList<>(),null);
        Formulaire formulaire2 = new Formulaire(null,new ArrayList<>(),null);
        Formulaire formulaire3 = new Formulaire(null,new ArrayList<>(),null);
        formulaireRepository.saveAllAndFlush(List.of(formulaire1,formulaire2,formulaire3));
        // 9 - Champs
            //Champs du formulaire 1
        Champ champ11 = new Champ(null,"text","invoice-number","","Numero de facture",formulaire1);
        Champ champ12 = new Champ(null,"email","email","","E-mail",formulaire1);
            //Champs du formulaire 2

        Champ champ21 = new Champ(null,"email","email","","E-mail",formulaire2);
        Champ champ22 = new Champ(null,"text","phone","","Téléphone",formulaire2);

        //Champs du formulaire 2

        Champ champ31 = new Champ(null,"text","contract-number","","Numero de contrat",formulaire3);
        Champ champ32 = new Champ(null,"text","phone","","Téléphone",formulaire3);
        champRepository.saveAllAndFlush(List.of(champ11,champ12,champ21,champ22,champ31,champ32));


        // 3 - Créanciers
        Creancier creancier1 = new Creancier(null,"IAM54","Maroc Telecom","logo_url");
        Creancier creancier2 = new Creancier(null,"RED35","Redal","logo_url");
        creancierRepository.saveAllAndFlush(List.of(
                creancier1,creancier2
        ));

        // 4 - Créances
        Creance creance1 = new Creance(null,"IAM-FI","IAM facture Internet",creancier1,formulaire1);
        Creance creance2 = new Creance(null,"IAM-RE","IAM Recharge",creancier1,formulaire2);
        Creance creance3 = new Creance(null,"RED-P","Paiement Redal",creancier2,formulaire3);
        creanceRepository.saveAllAndFlush(List.of(
                creance1,creance2,creance3
        ));
        formulaire1.setCreance(creance1);
        formulaire2.setCreance(creance2);
        formulaire3.setCreance(creance3);

        // I used cascade for the Creancier attribute, so it's persisted with the objects Creance

        creanceRepository.save(creance1);
        creanceRepository.save(creance2);
        creanceRepository.save(creance3);
        creanceRepository.flush();
        formulaireRepository.saveAllAndFlush(List.of(formulaire1,formulaire2,formulaire3));

        // 5 - Agences

        Agency agency1 = new Agency(null,"Agence Mhamid");
        Agency agency2 = new Agency(null,"Agence Gueliz");
        Agency agency3 = new Agency(null,"Agence Sidi Youssef");

        agencyRepository.saveAllAndFlush(List.of(agency1,agency2,agency3));

        // 6 - Agents
        AgentRegisterRequest agent1;
        AgentRegisterRequest agent2;
        agent1 = new AgentRegisterRequest("Ahmed","ELMOURABIT","CIN","EE850351","15-02-1994","NR 12 ASKEJOUR MARRAKECH","0632750369","ahmed2345","AEM23548","HH-53154","file_path",1L);
        agent2 = new AgentRegisterRequest("Karim","BENJELLOUN","CIN","KK586032","30-04-2000","NR 25 LMAARIF CASABLANCA","0653214123","karim@688","KBJ35489","ES-00354","file_path",1L);

        agentAuthService.register(agent1);
        agentAuthService.register(agent2);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        // 7 - Impayes
        Impaye impaye1,impaye2,impaye3,impaye4;
        try {
            impaye1 = new Impaye(null,"Facture internet 02/2023",50.0,"simple",false,sdf.parse("27-03-2023").getTime(),creance1,new ArrayList<>());
            impaye2 = new Impaye(null,"Facture internet 05/2023",50.0,"simple",false,sdf.parse("30-06-2023").getTime(),creance1,new ArrayList<>());
            impaye3 = new Impaye(null,"Facture internet 03/2023",50.0,"simple",false,sdf.parse("27-04-2023").getTime(),creance1,new ArrayList<>());
            impaye4 = new Impaye(null,"Facture Redal",550.0,"simple",false,sdf.parse("02-06-2023").getTime(),creance3,new ArrayList<>());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        impayeRepository.saveAllAndFlush(List.of(impaye1,impaye2,impaye3,impaye4));


        //Credentials of impaye1
        ImpayeCredential ic11 = new ImpayeCredential(null,"email","hamza@gmail.com",impaye1);
        ImpayeCredential ic12 = new ImpayeCredential(null,"invoice-number","192168",impaye1);

        //Credentials of impaye12
        ImpayeCredential ic121 = new ImpayeCredential(null,"email","hamza@gmail.com",impaye2);
        ImpayeCredential ic122 = new ImpayeCredential(null,"invoice-number","192168",impaye2);

        //credentials of impaye2
        ImpayeCredential ic21 = new ImpayeCredential(null,"email","hamdani@gmail.com",impaye3);
        ImpayeCredential ic22 = new ImpayeCredential(null,"invoice-number","55555",impaye3);

        //Credentials of impaye3
        ImpayeCredential ic31 = new ImpayeCredential(null,"contract-number","00109810",impaye4);
        ImpayeCredential ic32 = new ImpayeCredential(null,"phone","0634348550",impaye4);
        impayeCredentialRepository.saveAllAndFlush(List.of(ic11,ic12,ic121,ic122,ic21,ic22,ic31,ic32));


        // 10 - Paiements
        //Paiement p1 = new Paiement(null,new Date().getTime(),"3548 AA32 2335 6872",c1,impaye1);
        //Paiement p2 = new Paiement(null,new Date().getTime(),"2256 6576 9763 3487",c1,impaye2);



        //impaye1 is paid so ...
//        impaye1.setIsPaid(true);
//        impaye2.setIsPaid(true);
//        impayeRepository.saveAllAndFlush(List.of(impaye1,impaye3));

        //paiementRepository.saveAllAndFlush(List.of(p1,p2));




    }
}
