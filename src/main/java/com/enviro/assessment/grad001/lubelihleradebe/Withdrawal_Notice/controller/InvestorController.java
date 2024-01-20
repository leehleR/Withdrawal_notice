package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Investor;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.services.InvestorService;

@RestController
public class InvestorController {

    private final InvestorService investorService;

    @Autowired
    public InvestorController(InvestorService investorService) {
        this.investorService = investorService;
    }

    @PostMapping("investor/{id}/{name}%{lastname}")
    public ResponseEntity<Investor> createInvestor(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String lastName) {
        Investor user = investorService.createInvestor(id, name, lastName);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    
}
