package com.example.jl_entities.service;

import com.example.jl_entities.auth.bodies.register.UserRegisterRequest;
import com.example.jl_entities.auth.service.AuthService;
import com.example.jl_entities.entity.*;
import com.example.jl_entities.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private ImpayeRepository impayeRepository;
    private AgencyRepository agencyRepository;
    private ImpayeCredentialRepository impayeCredentialRepository;


    public void loadData(){
        //AuthService - Register Admin
        authService.register(new UserRegisterRequest("Admin","Admin","admin@jabaklah.ma","admin","admin"));

        //Account types
        AccountType accountType1 = new AccountType(null,1,2000.0);
        AccountType accountType2 = new AccountType(null,2,10000.0);
        AccountType accountType3 = new AccountType(null,3,100000.0);
        accountTypeRepository.saveAllAndFlush(List.of(accountType1,accountType2,accountType3));

        //Creancier
        Creancier creancier1=new Creancier(null,"iam","Maroc Telecom","../../assets/iam.png");
        Creancier creancier2=new Creancier(null,"no-creance","WinByInwi","../../assets/winByInwi.png");
        Creancier creancier3=new Creancier(null,"lydec","Lydec","../../assets/lydec.png");
        Creancier creancier4=new Creancier(null,"donation","ALCS","../../assets/alcs.png");
        Creancier creancier5=new Creancier(null,"total-maroc","TOTAL Maroc","../../assets/total-maroc.png");
        Creancier creancier6=new Creancier(null,"avito","Avito","../../assets/avito.png");
        creancierRepository.saveAllAndFlush(List.of(creancier1,creancier2,creancier3,creancier4,creancier5,creancier6));

        //Champ & Formulaire

            //MAROC TELECOM - MOBILE
            Formulaire formulaire1=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire1);
                Champ champ11=new Champ(null,"text","tel","","Numero de telephone mobile",formulaire1);
                Champ champ12=new Champ(null,"text","code-fidelio","","Code Fidelio",formulaire1);
                champRepository.saveAllAndFlush(List.of(champ11,champ12));
                    //update formulaire
                    formulaire1.getChamps().addAll(List.of(champ11,champ12));
//                    formulaireRepository.saveAndFlush(formulaire1);

            //MAROC TELECOM - FIXE
            Formulaire formulaire2=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire2);
                Champ champ21=new Champ(null,"text","tel","","Numero de telephone fixe",formulaire2);
                Champ champ22=new Champ(null,"text","code-confidentiel","","Code Confidentiel",formulaire2);
                champRepository.saveAllAndFlush(List.of(champ21,champ22));
                    //update formulaire
                    formulaire2.getChamps().addAll(List.of(champ21,champ22));
//                    formulaireRepository.saveAndFlush(formulaire2);

            //MAROC TELECOM - INTERNET
            Formulaire formulaire3=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire3);
                Champ champ31=new Champ(null,"text","identifiant-paiement","","Identifiant Paiement",formulaire3);
                champRepository.saveAllAndFlush(List.of(champ31));
                    //update formulaire
                    formulaire3.getChamps().addAll(List.of(champ31));
//                    formulaireRepository.saveAndFlush(formulaire3);

            //WIN BY INWI
            Formulaire formulaire4=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire4);
                Champ champ41=new Champ(null,"text","reference-fatourati","","Reference Fatourati",formulaire4);
                champRepository.saveAllAndFlush(List.of(champ41));
                    //update formulaire
                    formulaire4.getChamps().addAll(List.of(champ41));
//                    formulaireRepository.saveAndFlush(formulaire4);

            //LYDEC - PAIEMENT PAR REFERENCE
            Formulaire formulaire5=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire5);
                Champ champ51=new Champ(null,"text","reference","","Reference",formulaire5);
                champRepository.saveAllAndFlush(List.of(champ51));
                    //update formulaire
                    formulaire5.getChamps().addAll(List.of(champ51));
//                    formulaireRepository.saveAndFlush(formulaire5);

            //LYDEC - PAIEMENT PAR N DE CONTRAT
//            Formulaire formulaire6=new Formulaire(null,new ArrayList<>(),null);
//            formulaireRepository.saveAndFlush(formulaire6);
//                Champ champ61=new Champ(null,"text","numero-contrat","","Numero de Contrat",formulaire6);
//                Champ champ62=new Champ(null,"select","service","<option>Service</option><option>EAU</option><option>ELECTRICITE</option><option>MOYENNE TENSION</option>","",formulaire6);
//                champRepository.saveAllAndFlush(List.of(champ61,champ62));
//                    //update formulaire
//                    formulaire6.getChamps().addAll(List.of(champ61,champ62));
////                    formulaireRepository.saveAndFlush(formulaire6);

            //LYDEC - PAIEMENT PAR N DE FACTURE
            Formulaire formulaire7=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire7);
                Champ champ71=new Champ(null,"text","numero-facture","","Numero de Facture",formulaire7);
                champRepository.saveAllAndFlush(List.of(champ71));
                    //update formulaire
                    formulaire7.getChamps().addAll(List.of(champ71));
//                    formulaireRepository.saveAndFlush(formulaire7);

            //ALCS
            Formulaire formulaire8=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire8);
                Champ champ81=new Champ(null,"number","montant-don","","Montant du don",formulaire8);
                champRepository.saveAllAndFlush(List.of(champ81));
                    //update formulaire
                    formulaire8.getChamps().addAll(List.of(champ81));
//                    formulaireRepository.saveAndFlush(formulaire8);

            //TOTAL MAROC-BOOSTER
            Formulaire formulaire9=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire9);
                Champ champ91=new Champ(null,"text","reference-fatourati","","Reference Fatourati",formulaire9);
                Champ champ92=new Champ(null,"number","montant","","Montant",formulaire9);
                champRepository.saveAllAndFlush(List.of(champ91,champ92));
                    //update formulaire
                    formulaire9.getChamps().addAll(List.of(champ91,champ92));
//                    formulaireRepository.saveAndFlush(formulaire9);

            //TOTAL MAROC-RECHARGE CARTE
            Formulaire formulaire10=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire10);
                Champ champ101=new Champ(null,"text","reference-fatourati","","Reference Fatourati",formulaire10);
                Champ champ102=new Champ(null,"number","montant","","Montant",formulaire10);
                champRepository.saveAllAndFlush(List.of(champ101,champ102));
                    //update formulaire
                    formulaire10.getChamps().addAll(List.of(champ101,champ102));
//                    formulaireRepository.saveAndFlush(formulaire10);

            //AVITO
            Formulaire formulaire11=new Formulaire(null,new ArrayList<>(),null);
            formulaireRepository.saveAndFlush(formulaire11);
                Champ champ111=new Champ(null,"text","reference-fatourati","","Reference Fatourati",formulaire11);
                Champ champ112=new Champ(null,"number","montant","","Montant",formulaire11);
                champRepository.saveAllAndFlush(List.of(champ111,champ112));
                    //update formulaire
                    formulaire11.getChamps().addAll(List.of(champ111,champ112));
//                    formulaireRepository.saveAndFlush(formulaire11);

        //Creances

            //Creancier1 - MAROC TELECOM
            Creance creance11=new Creance(null,"11","Mobile",creancier1,formulaire1);
            Creance creance12=new Creance(null,"12","Fixe",creancier1,formulaire2);
            Creance creance13=new Creance(null,"13","Internet",creancier1,formulaire3);
            creanceRepository.saveAllAndFlush(List.of(creance11,creance12,creance13));
                //Update formulaires
                formulaire1.setCreance(creance11);
                formulaire2.setCreance(creance12);
                formulaire3.setCreance(creance13);
                formulaireRepository.saveAllAndFlush(List.of(formulaire1,formulaire2,formulaire3));

            //Creancier2 - WIN BY INWI
            Creance creance21=new Creance(null,"21","none",creancier2,formulaire4);
            creanceRepository.saveAndFlush(creance21);
                //Update formulaire
                formulaire4.setCreance(creance21);
                formulaireRepository.saveAndFlush(formulaire4);

            //Creancier3 - LYDEC
            Creance creance31=new Creance(null,"31","Paiement par reference",creancier3,formulaire5);
//            Creance creance32=new Creance(null,"32","Paiement par N de contrat",creancier3,formulaire6);
            Creance creance33=new Creance(null,"33","Paiement par N de facture",creancier3,formulaire7);
            creanceRepository.saveAllAndFlush(List.of(creance31,
//                    creance32,
                    creance33));
                //Update formulaires
                formulaire5.setCreance(creance31);
//                formulaire6.setCreance(creance32);
                formulaire7.setCreance(creance33);
                formulaireRepository.saveAllAndFlush(List.of(formulaire5,
//                        formulaire6,
                        formulaire7));

            //Creancier4 - ALCS
            Creance creance41=new Creance(null,"41","none",creancier4,formulaire8);
            creanceRepository.saveAndFlush(creance41);
                //Update formulaire
                    formulaire8.setCreance(creance41);
                formulaireRepository.saveAndFlush(formulaire8);

            //Creancier5 - TOTAL MAROC
            Creance creance51=new Creance(null,"51","Booster",creancier5,formulaire9);
            Creance creance52=new Creance(null,"52","Recharge carte",creancier5,formulaire10);
            creanceRepository.saveAllAndFlush(List.of(creance51,creance52));
                //Update formulaires
                formulaire9.setCreance(creance51);
                formulaire10.setCreance(creance52);
                formulaireRepository.saveAllAndFlush(List.of(formulaire9,formulaire10));

            //Creancier6 - AVITO
            Creance creance61=new Creance(null,"61","none",creancier6,formulaire11);
            creanceRepository.saveAndFlush(creance61);
                //Update formulaire
                formulaire11.setCreance(creance61);
                formulaireRepository.saveAndFlush(formulaire11);

        //Impaye

            //Creancier1 - MAROC TELECOM
                //Creance11 - MOBILE
                Impaye impaye111=new Impaye(null,"Facture Mobile",200.00,"simple",false, Timestamp.valueOf(LocalDateTime.of(2023,05,01,00,00)).getTime(),creance11,new ArrayList<>());
                Impaye impaye112=new Impaye(null,"Facture Mobile",200.00,"simple",false, Timestamp.valueOf(LocalDateTime.of(2023,04,01,00,00)).getTime(),creance11,new ArrayList<>());
                Impaye impaye113=new Impaye(null,"Facture Mobile",200.00,"simple",false, Timestamp.valueOf(LocalDateTime.of(2023,03,01,00,00)).getTime(),creance11,new ArrayList<>());
                Impaye impaye114=new Impaye(null,"Facture Mobile",200.00,"simple",false, Timestamp.valueOf(LocalDateTime.of(2023,02,01,00,00)).getTime(),creance11,new ArrayList<>());
                Impaye impaye115=new Impaye(null,"Facture Mobile",200.00,"simple",false, Timestamp.valueOf(LocalDateTime.of(2022,12,01,00,00)).getTime(),creance11,new ArrayList<>());
                //impaye de retard
                Impaye impaye116=new Impaye(null,"Penalite de retard",0.025,"penalite",false, Timestamp.valueOf(LocalDateTime.of(2022,01,01,00,00)).getTime(),creance11,new ArrayList<>());
                impayeRepository.saveAllAndFlush(List.of(impaye111,impaye112,impaye113,impaye114,impaye115,impaye116));
                    //credentials - group 1
                        //impaye111
                        ImpayeCredential impayeCredential111=new ImpayeCredential(null,formulaire1.getChamps().get(0).getName(),"0676168831",impaye111);
                        ImpayeCredential impayeCredential112=new ImpayeCredential(null,formulaire1.getChamps().get(1).getName(),"10000",impaye111);
                        //impaye112
                        ImpayeCredential impayeCredential113=new ImpayeCredential(null,formulaire1.getChamps().get(0).getName(),"0676168831",impaye112);
                        ImpayeCredential impayeCredential114=new ImpayeCredential(null,formulaire1.getChamps().get(1).getName(),"10000",impaye112);
                        //impaye113
                        ImpayeCredential impayeCredential115=new ImpayeCredential(null,formulaire1.getChamps().get(0).getName(),"0676168831",impaye113);
                        ImpayeCredential impayeCredential116=new ImpayeCredential(null,formulaire1.getChamps().get(1).getName(),"10000",impaye113);
                        impayeCredentialRepository.saveAllAndFlush(List.of(impayeCredential111,impayeCredential112,impayeCredential113,impayeCredential114,impayeCredential115,impayeCredential116));
                    //credentials - group2
                        //impaye114
                        ImpayeCredential impayeCredential117=new ImpayeCredential(null,formulaire1.getChamps().get(0).getName(),"0677168831",impaye114);
                        ImpayeCredential impayeCredential118=new ImpayeCredential(null,formulaire1.getChamps().get(1).getName(),"10001",impaye114);
                        //impaye115
                        ImpayeCredential impayeCredential119=new ImpayeCredential(null,formulaire1.getChamps().get(0).getName(),"0677168831",impaye115);
                        ImpayeCredential impayeCredential1110=new ImpayeCredential(null,formulaire1.getChamps().get(1).getName(),"10001",impaye115);
                        impayeCredentialRepository.saveAllAndFlush(List.of(impayeCredential117,impayeCredential118,impayeCredential119,impayeCredential1110));
                //Creance13 - INTERNET
                Impaye impaye131=new Impaye(null,"Facture Internet ADSL",250.00,"simple",false, Timestamp.valueOf(LocalDateTime.of(2023,05,01,00,00)).getTime(),creance13,new ArrayList<>());
                Impaye impaye132=new Impaye(null,"Facture Internet FIBRE",500.00,"simple",false, Timestamp.valueOf(LocalDateTime.of(2023,01,01,00,00)).getTime(),creance13,new ArrayList<>());
                //impaye de retard
                Impaye impaye133=new Impaye(null,"Penalite de retard",0.05,"penalite",false,Timestamp.valueOf(LocalDateTime.of(2023,01,01,00,00,00)).getTime(),creance13,new ArrayList<>());
                impayeRepository.saveAllAndFlush(List.of(impaye131,impaye132,impaye133));
                    //credentials - group 1
                        //impaye131
                        ImpayeCredential impayeCredential131=new ImpayeCredential(null,formulaire3.getChamps().get(0).getName(),"12345678",impaye131);
                        impayeCredentialRepository.saveAndFlush(impayeCredential131);
                    //creadentials - group 2
                        //impaye132
                        ImpayeCredential impayeCredential132=new ImpayeCredential(null,formulaire3.getChamps().get(0).getName(),"12300000",impaye132);
                        impayeCredentialRepository.saveAndFlush(impayeCredential132);

            //Creancier2 - WIN BY INWI
                //Creance21 - none
                Impaye impaye211=new Impaye(null,"Achats par internet",169.00,"simple",false,Timestamp.valueOf(LocalDateTime.of(2023,05,01,00,00,00)).getTime(),creance21,new ArrayList<>());
                Impaye impaye212=new Impaye(null,"Achats par internet",169.00,"simple",false,Timestamp.valueOf(LocalDateTime.of(2023,04,01,00,00,00)).getTime(),creance21,new ArrayList<>());
                Impaye impaye213=new Impaye(null,"Achats par internet",169.00,"simple",false,Timestamp.valueOf(LocalDateTime.of(2023,05,01,00,00,00)).getTime(),creance21,new ArrayList<>());
                impayeRepository.saveAllAndFlush(List.of(impaye211,impaye212,impaye213));
                    //credentials - group 1
                        //impaye211
                        ImpayeCredential impayeCredential211=new ImpayeCredential(null,formulaire4.getChamps().get(0).getName(),"12341165",impaye211);
                        impayeCredentialRepository.saveAndFlush(impayeCredential211);
                        //impaye212
                        ImpayeCredential impayeCredential212=new ImpayeCredential(null,formulaire4.getChamps().get(0).getName(),"12341165",impaye212);
                        impayeCredentialRepository.saveAndFlush(impayeCredential212);
                    //credentials - group 2
                        //impaye213
                        ImpayeCredential impayeCredential213=new ImpayeCredential(null,formulaire4.getChamps().get(0).getName(),"34560987",impaye213);
                        impayeCredentialRepository.saveAndFlush(impayeCredential213);

            //Creancier3 - Lydec
                //Creance32 - Paiement par N de Contrat
//                Impaye impaye321=new Impaye(null,"Paiement par N de Contrat",400.00,"simple",false,Timestamp.valueOf(LocalDateTime.of(2023,02,01,00,00,00)).getTime(),creance32,new ArrayList<>());
//                Impaye impaye322=new Impaye(null,"Paiement par N de Contrat",322.40,"simple",false,Timestamp.valueOf(LocalDateTime.of(2023,01,01,00,00,00)).getTime(),creance32,new ArrayList<>());
//                impayeRepository.saveAllAndFlush(List.of(impaye321,impaye322));
//                    //credentials - group 1
//                        //impaye321
//                        ImpayeCredential impayeCredential321=new ImpayeCredential(null,formulaire6.getChamps().get(0).getName(),"12345678",impaye321);
//                        ImpayeCredential impayeCredential322=new ImpayeCredential(null,formulaire6.getChamps().get(0).getName(),"EAU",impaye321);
//                        impayeCredentialRepository.saveAllAndFlush(List.of(impayeCredential321,impayeCredential322));
//                        //impaye322
//                        ImpayeCredential impayeCredential323=new ImpayeCredential(null,formulaire6.getChamps().get(0).getName(),"12345678",impaye322);
//                        ImpayeCredential impayeCredential324=new ImpayeCredential(null,formulaire6.getChamps().get(0).getName(),"EAU",impaye322);
//                        impayeCredentialRepository.saveAllAndFlush(List.of(impayeCredential323,impayeCredential324));

            //Creancier4 - ALCS

                //Creance41 - none
                Impaye impaye411=new Impaye(null,"Don ALCS",0.0,"simple",false,0L,creance41,new ArrayList<>());
                impayeRepository.saveAndFlush(impaye411);
                    //credentials
                        //impaye411
                        ImpayeCredential impayeCredential411=new ImpayeCredential(null,formulaire8.getChamps().get(0).getName(),"0",impaye411);
                        impayeCredentialRepository.saveAndFlush(impayeCredential411);



        //Agences
        Agency agency1 = new Agency(null,"Agence Mhamid");
        Agency agency2 = new Agency(null,"Agence Gueliz");
        agencyRepository.saveAllAndFlush(List.of(agency1,agency2));
    }
}
