package com.example.jl_entities.service;

import com.example.jl_entities.auth.bodies.register.UserRegisterRequest;
import com.example.jl_entities.auth.service.AuthService;
import com.example.jl_entities.entity.*;
import com.example.jl_entities.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InitialDataService {

    private AuthService authService;

    private AccountTypeRepository accountTypeRepository;
    private CreancierRepository creancierRepository;
    private CreanceRepository creanceRepository;
    private FormulaireRepository formulaireRepository;
    private ChampRepository champRepository;
    private AgencyRepository agencyRepository;


    public void loadData(){
        //AuthService - Register Admin
        authService.register(new UserRegisterRequest("Admin","Admin","admin@jabaklah.ma","admin","admin"));

        //Account types
        AccountType accountType1 = new AccountType(null,1,200.0);
        AccountType accountType2 = new AccountType(null,2,2000.0);
        AccountType accountType3 = new AccountType(null,3,50000.0);
        accountTypeRepository.saveAllAndFlush(List.of(accountType1,accountType2,accountType3));

        //Creancier
        Creancier creancier1=new Creancier(null,"iam","Maroc Telecom","assets/iam.jpg");
        Creancier creancier2=new Creancier(null,"orange","Orange","assets/orange.jpg");
        Creancier creancier3=new Creancier(null,"lydec","Lydec","assets/lydec.jpg");
        Creancier creancier4=new Creancier(null,"alcs","ALCS","assets/alcs.jpg");
        Creancier creancier5=new Creancier(null,"total-maroc","TOTAL Maroc","assets/total-maroc.jpg");
        Creancier creancier6=new Creancier(null,"avito","Avito","assets/avito.jpg");
        creancierRepository.saveAllAndFlush(List.of(creancier1,creancier2,creancier3,creancier4,creancier5,creancier6));

        //Champ & Formulaire

            //MAROC TELECOM - MOBILE
            Formulaire formulaire1=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire1);
                Champ champ11=new Champ(null,"text","tel","","Numero de telephone mobile",formulaire1);
                Champ champ12=new Champ(null,"text","code-fidelio","","Code Fidelio",formulaire1);
                champRepository.saveAllAndFlush(List.of(champ11,champ12));

            //MAROC TELECOM - FIXE
            Formulaire formulaire2=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire2);
                Champ champ21=new Champ(null,"text","tel","","Numero de telephone fixe",formulaire2);
                Champ champ22=new Champ(null,"text","code-confidentiel","","Code Confidentiel",formulaire2);
                champRepository.saveAllAndFlush(List.of(champ21,champ22));

            //MAROC TELECOM - INTERNET
            Formulaire formulaire3=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire3);
                Champ champ31=new Champ(null,"text","identifiant-paiement","","Identifiant Paiement",formulaire3);
                champRepository.saveAllAndFlush(List.of(champ31));

            //WIN BY INWI
            Formulaire formulaire4=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire4);
                Champ champ41=new Champ(null,"text","reference-fatourati","","Reference Fatourati",formulaire4);
                champRepository.saveAllAndFlush(List.of(champ41));

            //LYDEC - PAIEMENT PAR REFERENCE
            Formulaire formulaire5=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire5);
                Champ champ51=new Champ(null,"text","reference","","Reference",formulaire5);
                champRepository.saveAllAndFlush(List.of(champ51));

            //LYDEC - PAIEMENT PAR N DE CONTRAT
            Formulaire formulaire6=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire6);
                Champ champ61=new Champ(null,"text","numero-contrat","","Numero de Contrat",formulaire6);
                Champ champ62=new Champ(null,"select","service","<option>Service</option><option>EAU</option><option>ELECTRICITÉ</option><option>MOYENNE TENSION</option>","",formulaire6);
                champRepository.saveAllAndFlush(List.of(champ61,champ62));

            //LYDEC - PAIEMENT PAR N DE FACTURE
            Formulaire formulaire7=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire7);
                Champ champ71=new Champ(null,"text","numero-facture","","Numero de Facture",formulaire7);
                champRepository.saveAllAndFlush(List.of(champ71));

            //ALCS
            Formulaire formulaire8=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire8);
                Champ champ81=new Champ(null,"number","montant-don","","Montant du don",formulaire8);
                champRepository.saveAllAndFlush(List.of(champ81));

            //TOTAL MAROC-BOOSTER
            Formulaire formulaire9=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire9);
                Champ champ91=new Champ(null,"text","reference-fatourati","","Reference Fatourati",formulaire9);
                Champ champ92=new Champ(null,"number","montant","","Montant",formulaire9);
                champRepository.saveAllAndFlush(List.of(champ91,champ92));

            //TOTAL MAROC-RECHARGE CARTE
            Formulaire formulaire10=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire10);
                Champ champ101=new Champ(null,"text","reference-fatourati","","Reference Fatourati",formulaire10);
                Champ champ102=new Champ(null,"number","montant","","Montant",formulaire10);
                champRepository.saveAllAndFlush(List.of(champ101,champ102));

            //AVITO
            Formulaire formulaire11=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire11);
                Champ champ111=new Champ(null,"text","reference-fatourati","","Reference Fatourati",formulaire11);
                Champ champ112=new Champ(null,"number","montant","","Montant",formulaire11);
                champRepository.saveAllAndFlush(List.of(champ111,champ112));

        //Creances

            //Creancier1 - MAROC TELECOM
            Creance creance11=new Creance(null,"","Mobile",creancier1,formulaire1);
            Creance creance12=new Creance(null,"","Fixe",creancier1,formulaire2);
            Creance creance13=new Creance(null,"","Internet",creancier1,formulaire3);
            creanceRepository.saveAllAndFlush(List.of(creance11,creance12,creance13));
                //Update formulaires
                formulaire1.setCreance(creance11);
                formulaire2.setCreance(creance12);
                formulaire3.setCreance(creance13);
                formulaireRepository.saveAllAndFlush(List.of(formulaire1,formulaire2,formulaire3));

            //Creancier2 - WIN BY INWI
            Creance creance21=new Creance(null,"","none",creancier2,formulaire4);
            creanceRepository.saveAndFlush(creance21);
                //Update formulaire
                formulaire4.setCreance(creance21);
                formulaireRepository.saveAndFlush(formulaire4);

            //Creancier3 - LYDEC
            Creance creance31=new Creance(null,"","Paiement par reference",creancier3,formulaire5);
            Creance creance32=new Creance(null,"","Paiement par N de contrat",creancier3,formulaire6);
            Creance creance33=new Creance(null,"","Paiement par N de facture",creancier3,formulaire7);
            creanceRepository.saveAllAndFlush(List.of(creance31,creance32,creance33));
                //Update formulaires
                formulaire5.setCreance(creance31);
                formulaire6.setCreance(creance32);
                formulaire7.setCreance(creance33);
                formulaireRepository.saveAllAndFlush(List.of(formulaire5,formulaire6,formulaire7));

            //Creancier4 - ALCS
            Creance creance41=new Creance(null,"","none",creancier4,formulaire8);
            creanceRepository.saveAndFlush(creance41);
                //Update formulaire
                formulaire8.setCreance(creance41);
                formulaireRepository.saveAndFlush(formulaire8);

            //Creancier5 - TOTAL MAROC
            Creance creance51=new Creance(null,"","Booster",creancier5,formulaire9);
            Creance creance52=new Creance(null,"","Recharge carte",creancier5,formulaire10);
            creanceRepository.saveAllAndFlush(List.of(creance51,creance52));
                //Update formulaires
                formulaire9.setCreance(creance51);
                formulaire10.setCreance(creance52);
                formulaireRepository.saveAllAndFlush(List.of(formulaire9,formulaire10));

            //Creancier6 - AVITO
            Creance creance61=new Creance(null,"","none",creancier6,formulaire11);
            creanceRepository.saveAndFlush(creance61);
                //Update formulaire
                formulaire11.setCreance(creance61);
                formulaireRepository.saveAndFlush(formulaire11);

        //Agences
        Agency agency1 = new Agency(null,"Agence Mhamid");
        Agency agency2 = new Agency(null,"Agence Gueliz");
        agencyRepository.saveAllAndFlush(List.of(agency1,agency2));
    }
}
