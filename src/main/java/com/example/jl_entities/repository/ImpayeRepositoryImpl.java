package com.example.jl_entities.repository;

import com.example.jl_entities.CreanceNotFoundException;
import com.example.jl_entities.CredentialsRequest;
import com.example.jl_entities.entity.Champ;
import com.example.jl_entities.entity.Creance;
import com.example.jl_entities.entity.Formulaire;
import com.example.jl_entities.entity.Impaye;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ImpayeRepositoryImpl{
    @PersistenceContext
    private EntityManager em;
    private final CreanceRepository creanceRepository;
    private final ChampRepository champRepository;

    public ImpayeRepositoryImpl(CreanceRepository creanceRepository, ChampRepository champRepository) {
        this.creanceRepository = creanceRepository;
        this.champRepository = champRepository;
    }


    public List<Impaye> findAllByCredentials2(CredentialsRequest request){
        Creance c = creanceRepository.findById(request.getCreanceId()).orElse(null);
        Formulaire form = c.getFormulaire();
        List<Champ> champs = form.getChamps();
        List<String> champsNames = champs.stream().map(Champ::getName).toList();
        String query = "SELECT * FROM impaye \n" +
                "WHERE (creance_id="+request.getCreanceId()+") \n AND \n id IN ( " ;
        List<String> intersections  = new ArrayList<>();
        for(String s : champsNames){
            String selectCredential = "( \n" +
                    "        SELECT impaye_id FROM impaye_credential WHERE \n" +
                    "        (impaye_credential.cred_name='"+s+"' AND impaye_credential.cred_value='"+request.getCredentials().get(s)+"') \n" +
                    "        )";
            intersections.add(selectCredential);
        }
        String subQuery = String.join(" INTERSECT ",intersections);
        query += subQuery+" \n );";
        System.out.println(query);
        return em.createNativeQuery(query,Impaye.class).getResultList();
    }
    public List<Impaye> findAllByCredentials(CredentialsRequest request) throws CreanceNotFoundException {
        Creance c = creanceRepository.findById(request.getCreanceId()).orElse(null);
        if(c==null) throw new CreanceNotFoundException("creance not found");
        Formulaire form = c.getFormulaire();
        List<Champ> champs = form.getChamps();
        List<String> champsNames = champs.stream().map(Champ::getName).toList();
        List<String> intersections  = new ArrayList<>();
        int i =1;
        String query = "SELECT * FROM impaye \n " ;
        for(String s : champsNames){
            System.out.println(s);
            String selectCredential = "JOIN (\n" +
                    "    SELECT impaye_id\n" +
                    "    FROM impaye_credential\n" +
                    "    WHERE (cred_name = '"+s+"' AND cred_value = '"+request.getCredentials().get(s)+"')\n" +
                    ") AS subquery"+i+" ON impaye.id = subquery"+i+".impaye_id";
            intersections.add(selectCredential);
            i++;
        }
        String subQuery = String.join("\n",intersections);
        query += subQuery+"\n WHERE creance_id = "+request.getCreanceId()+";";
        System.out.println(query);
        return em.createNativeQuery(query,Impaye.class).getResultList();
    }
}
