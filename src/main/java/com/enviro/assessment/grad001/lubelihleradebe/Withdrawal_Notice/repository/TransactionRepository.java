package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    Optional<Transaction> findByInvestor_Id(Long investor_Id);

    
}
