package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Investor;

public interface InvestorRepository extends JpaRepository < Investor, String>{

    Optional<Investor> findByIdAndFirstnameAndLastname(long id, String firstName, String lastname);
    
}
