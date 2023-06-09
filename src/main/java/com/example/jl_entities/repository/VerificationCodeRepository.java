package com.example.jl_entities.repository;

import com.example.jl_entities.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode,Long> {
    @Query(value="SELECT * FROM verification_code WHERE client_id=:clientId",nativeQuery = true)
    public List<VerificationCode> findByClientId(Long clientId);
}
