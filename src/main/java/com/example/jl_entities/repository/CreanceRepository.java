package com.example.jl_entities.repository;

import com.example.jl_entities.entity.AccountType;
import com.example.jl_entities.entity.Creance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreanceRepository extends JpaRepository<Creance,Long> {

    List<Creance> findAllByCreancierId(long id);
}
