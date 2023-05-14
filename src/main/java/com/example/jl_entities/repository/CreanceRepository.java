package com.example.jl_entities.repository;

import com.example.jl_entities.entity.AccountType;
import com.example.jl_entities.entity.Creance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreanceRepository extends JpaRepository<Creance,Long> {
}
