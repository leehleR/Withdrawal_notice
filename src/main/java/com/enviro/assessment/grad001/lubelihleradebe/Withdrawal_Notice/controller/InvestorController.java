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
    public List<Investor> getInvestors(){
        return investorService.findAllInvestors();
    }

    @PostMapping
    public ResponseEntity<Investor> createInvestor(@RequestBody Investor investor){
        Investor addInvestor = investorService.addInvestor(investor);
        return ResponseEntity.ok(addInvestor);
    }

    @GetMapping("/{investorId}")
    public ResponseEntity<Investor> getInvestorInformation(@PathVariable Long investorId){
        Investor foundInvestor = investorService.findInvestorProducts(investorId);


        return ResponseEntity.ok(foundInvestor);
    }

}
