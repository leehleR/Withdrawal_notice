package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.repository;


import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvestorRepository extends JpaRepository <Investor, Long> {
    @Query("SELECT i FROM Investor i LEFT JOIN FETCH i.productsList WHERE i.investorId = :investorId")
    Investor findInvestorProductsById(Long investorId);


    @Query("SELECT i FROM Investor i LEFT JOIN FETCH i.productsList")
    List<Investor> findAllInvestors();
}
