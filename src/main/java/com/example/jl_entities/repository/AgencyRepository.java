package com.example.jl_entities.repository;

import com.example.jl_entities.entity.Agency;
import com.example.jl_entities.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyRepository extends JpaRepository<Agency,Long> {
}
