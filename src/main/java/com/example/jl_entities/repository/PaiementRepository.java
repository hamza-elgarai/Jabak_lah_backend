package com.example.jl_entities.repository;

import com.example.jl_entities.entity.Client;
import com.example.jl_entities.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaiementRepository extends JpaRepository<Paiement,Long> {
    List<Paiement> findPaiementsByClientId(Long clientId);
}
