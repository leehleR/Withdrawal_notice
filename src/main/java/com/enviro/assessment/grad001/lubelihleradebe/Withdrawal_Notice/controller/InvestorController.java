package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.controller;


import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Investor;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("enviro/investor")
public class InvestorController {

    private final InvestorService investorService;

    @Autowired
    public InvestorController(InvestorService investorService) {
        this.investorService = investorService;
    }

    @GetMapping
    public List<Investor> GetInvestors(){
        return investorService.findAllInvestors();
    }

    @PostMapping
    public ResponseEntity<Investor> createInvestor(@RequestBody Investor investor){
        Investor addInvestor = investorService.addInvestor(investor);
        return ResponseEntity.ok(addInvestor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investor> getInvestorInformation(@PathVariable Long id){
        Investor findInvestor = investorService.findInvestor(id);
        return ResponseEntity.ok(findInvestor);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<Investor> getInvestorProducts(@PathVariable Long investorId){
        Investor products = investorService.findInvestorProducts(investorId);
        return ResponseEntity.ok(products);
    }
}
