package com.example.jl_entities.repository;

import com.example.jl_entities.entity.Champ;
import com.example.jl_entities.entity.Formulaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormulaireRepository extends JpaRepository<Formulaire,Long> {
    List<Formulaire> findAllByCreanceId(long id);
}
