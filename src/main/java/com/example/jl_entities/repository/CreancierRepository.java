package com.example.jl_entities.repository;

import com.example.jl_entities.entity.AccountType;
import com.example.jl_entities.entity.Creancier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreancierRepository extends JpaRepository<Creancier,Long> {
}
