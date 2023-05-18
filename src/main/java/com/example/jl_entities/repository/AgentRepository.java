package com.example.jl_entities.repository;

import com.example.jl_entities.entity.Agency;
import com.example.jl_entities.entity.Agent;
import com.example.jl_entities.entity.Formulaire;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent,Long> {
    Optional<Agent> findByUsername(String username);
}
