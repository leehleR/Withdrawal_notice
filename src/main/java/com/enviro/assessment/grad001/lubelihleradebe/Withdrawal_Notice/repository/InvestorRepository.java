package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.repository;


import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvestorRepository extends JpaRepository <Investor, Long> {
    @Query("SELECT p FROM Investor p LEFT JOIN FETCH p.productsList WHERE p.investorId = :investorId")
    Investor findInvestorProductsById(long investorId);
}
