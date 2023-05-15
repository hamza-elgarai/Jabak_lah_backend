package com.example.jl_entities.repository;

import com.example.jl_entities.entity.ImpayeCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpayeCredentialRepository extends JpaRepository<ImpayeCredential,Long> {
}
