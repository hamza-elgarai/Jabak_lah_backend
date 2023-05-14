package com.example.jl_entities.repository;

import com.example.jl_entities.entity.AccountType;
import com.example.jl_entities.entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType,Long> {
}
