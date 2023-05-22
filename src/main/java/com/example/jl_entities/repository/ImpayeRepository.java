package com.example.jl_entities.repository;

import com.example.jl_entities.CredentialsRequest;
import com.example.jl_entities.entity.Agency;
import com.example.jl_entities.entity.Creance;
import com.example.jl_entities.entity.Impaye;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface ImpayeRepository extends JpaRepository<Impaye,Long> {
    List<Impaye> findAllByCreanceId(long id);

}
