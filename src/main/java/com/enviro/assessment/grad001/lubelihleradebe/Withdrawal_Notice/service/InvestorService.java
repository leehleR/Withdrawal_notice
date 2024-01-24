package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.service;

import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Investor;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.repository.InvestorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorService {
    private final InvestorRepository investorRepository;

    @Autowired
    public InvestorService(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    public List<Investor> findAllInvestors(){
        return investorRepository.findAll();
    }


    @Transactional
    public Investor findInvestor(Long investorId){
        return investorRepository.findById(investorId)
                .orElseThrow(() -> new IllegalArgumentException("Investor not found"));
    }

    public Investor addInvestor(Investor investor){
//        List<String> productTypes = investor.getProductTypes();
//        investor.setProductTypes(productTypes);
        return investorRepository.save(investor);
    }

    public void deleteInvestor(Long investorId){
        investorRepository.deleteById(investorId);
    }

    public Investor findInvestorProducts(long investorId){
        return investorRepository.findInvestorProductsById(investorId);
    }
}
