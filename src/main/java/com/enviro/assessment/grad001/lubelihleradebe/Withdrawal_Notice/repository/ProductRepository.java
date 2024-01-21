package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Investor;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Product;


public interface ProductRepository extends JpaRepository < Product, Long>{
    ArrayList<Product> findByInvestor_Id(Long investor_Id);
}
