package com.example.jl_entities.repository;

import com.example.jl_entities.entity.Agent;
import com.example.jl_entities.entity.Formulaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent,Long> {
}
