package com.example.jl_entities.repository;

import com.example.jl_entities.entity.Agency;
import com.example.jl_entities.entity.Impaye;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImpayeRepository extends JpaRepository<Impaye,Long> {
}
