package com.example.jl_entities.service;

import com.example.jl_entities.CredentialsRequest;
import com.example.jl_entities.entity.*;
import com.example.jl_entities.repository.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FakeDataLoadService {
    private AccountTypeRepository accountTypeRepository;
    private AgentRepository agentRepository;
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

    public FakeDataLoadService(AccountTypeRepository accountTypeRepository, AgentRepository agentRepository, AgencyRepository agencyRepository, ChampRepository champRepository, ClientRepository clientRepository, CreanceRepository creanceRepository, CreancierRepository creancierRepository, FormulaireRepository formulaireRepository, ImpayeRepository impayeRepository, PaiementRepository paiementRepository,ImpayeCredentialRepository impayeCredentialRepository,ImpayeRepositoryImpl impayeRepositoryImpl) {
        this.accountTypeRepository = accountTypeRepository;
        this.agentRepository = agentRepository;
        this.agencyRepository = agencyRepository;
        this.champRepository = champRepository;
        this.clientRepository = clientRepository;
        this.creanceRepository = creanceRepository;
        this.creancierRepository = creancierRepository;
        this.formulaireRepository = formulaireRepository;
        this.impayeRepository = impayeRepository;
        this.paiementRepository = paiementRepository;
        this.impayeCredentialRepository = impayeCredentialRepository;
        this.impayeRepositoryImpl = impayeRepositoryImpl;
    }
    public List<Impaye> loadImpaye(CredentialsRequest request){
        return impayeRepositoryImpl.findAllByCredentials(request);
    }
    public void loadData(){

        // 1 - Account types
        AccountType ac1 = new AccountType(null,1,200.0);
        AccountType ac2 = new AccountType(null,2,2000.0);
        AccountType ac3 = new AccountType(null,3,50000.0);
        accountTypeRepository.save(ac1);
        accountTypeRepository.save(ac2);
        accountTypeRepository.save(ac3);
        accountTypeRepository.flush();


        // 2 - Clients
        Client c1 = new Client(null,"Hamza","ELGARAI","hamzaelgarai10@gmail.com","0634348550","hamza1234","1234 5678 9101 1123",ac1,new ArrayList<>(),500.0,"verified");
        Client c2 = new Client(null,"Mohamed","HAMDANI","hamdanimee@gmail.com","0632751035","medqs546","0003 1234 2434 3863",ac3,new ArrayList<>(),5800.0,"verified");
        Client c3 = new Client(null,"Mohamed","BENAARROUCH","medbenarr20@gmail.com","06359820348","medben3544","5603 8976 5722 3540",ac2,new ArrayList<>(),1800.0,"pending");
        clientRepository.save(c1);
        clientRepository.save(c2);
        clientRepository.save(c3);
        clientRepository.flush();


        // 8 - Formulaires
        Formulaire formulaire1 = new Formulaire(null,new ArrayList<>(),null);
        Formulaire formulaire2 = new Formulaire(null,new ArrayList<>(),null);
        formulaireRepository.saveAllAndFlush(List.of(formulaire1,formulaire2));
        // 9 - Champs
            //Champs du formulaire 1
        Champ champ11 = new Champ(null,"text","invoice-number","","Numero de facture",formulaire1);
        Champ champ12 = new Champ(null,"email","email","","E-mail",formulaire1);
            //Champs du formulaire 2

        Champ champ21 = new Champ(null,"text","email","","E-mail",formulaire2);
        Champ champ22 = new Champ(null,"text","phone","","Téléphone",formulaire2);
        champRepository.saveAllAndFlush(List.of(champ11,champ12,champ21,champ22));


        // 3 - Créanciers
        Creancier creancier1 = new Creancier(null,"IAM54","Maroc Telecom","logo_url");
        Creancier creancier2 = new Creancier(null,"RED35","Redal","logo_url");
        // 4 - Créances
        Creance creance1 = new Creance(null,"IAM-FI","IAM facture Internet",creancier1,formulaire1);
        Creance creance2 = new Creance(null,"IAM-RE","IAM Recharge",creancier1,formulaire1);
        Creance creance3 = new Creance(null,"RED-P","Paiement Redal",creancier2,formulaire2);

        // I used cascade for the Creancier attribute so it's persisted with the objects Creance

        creanceRepository.save(creance1);
        creanceRepository.save(creance2);
        creanceRepository.save(creance3);
        creanceRepository.flush();

        // 5 - Agences

        Agency agency1 = new Agency(null,"Tashilat");
        Agency agency2 = new Agency(null,"Cashplus");

        agencyRepository.saveAllAndFlush(List.of(agency1,agency2));

        // 6 - Agents

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Agent agent1 = null;
        Agent agent2 = null;
        try {
            agent1 = new Agent(null,"Ahmed","ELMOURABIT","CIN","EE850351",sdf.parse("15-02-1994"),"NR 12 ASKEJOUR MARRAKECH","0632750369","ahmed2345","AEM23548","HH-53154","file_path",agency1);
            agent2 = new Agent(null,"Karim","BENJELLOUN","CIN","KK586032",sdf.parse("30-04-2000"),"NR 25 LMAARIF CASABLANCA","0653214123","karim@688","KBJ35489","ES-00354","file_path",agency1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        agentRepository.saveAllAndFlush(List.of(agent1,agent2));

        // 7 - Impayes
        Impaye impaye1,impaye2,impaye3=null;
        try {
            impaye1 = new Impaye(null,"Facture internet 02/2023",250.0,"simple",false,sdf.parse("27-03-2023"),creance1,new ArrayList<ImpayeCredential>());
            impaye2 = new Impaye(null,"Facture internet 03/2023",250.0,"simple",false,sdf.parse("27-04-2023"),creance1,new ArrayList<ImpayeCredential>());
            impaye3 = new Impaye(null,"Facture Redal",1550.0,"simple",false,sdf.parse("02-06-2023"),creance3,new ArrayList<ImpayeCredential>());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        impayeRepository.saveAllAndFlush(List.of(impaye1,impaye2,impaye3));


        //Credentials of impaye1
        ImpayeCredential ic11 = new ImpayeCredential(null,"email","hamza@gmail.com",impaye1);
        ImpayeCredential ic12 = new ImpayeCredential(null,"invoice-number","192168",impaye1);

        //credentials of impaye2
        ImpayeCredential ic21 = new ImpayeCredential(null,"email","hamza@gmail.com",impaye2);
        ImpayeCredential ic22 = new ImpayeCredential(null,"phone","0634348550",impaye2);

        //Credentials of impaye3
        ImpayeCredential ic31 = new ImpayeCredential(null,"email","hamza@gmail.com",impaye3);
        ImpayeCredential ic32 = new ImpayeCredential(null,"phone","0634348550",impaye3);
        impayeCredentialRepository.saveAllAndFlush(List.of(ic11,ic12,ic21,ic22,ic31,ic32));


        // 10 - Paiements
        Paiement p1 = new Paiement(null,new Date().getTime(),"3548 AA32 2335 6872",c1,impaye1);
        Paiement p2 = new Paiement(null,new Date().getTime(),"2256 6576 9763 3487",c1,impaye2);



        //impaye1 is paid so ...
        impaye1.setIsPaid(true);
        impaye2.setIsPaid(true);
        impayeRepository.saveAllAndFlush(List.of(impaye1,impaye2));

        paiementRepository.saveAllAndFlush(List.of(p1,p2));




    }
}
