package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    // @PostMapping("/investor/{id}/{firstName}/{lastName}")
    // public ResponseEntity<Investor> createInvestor(
    //         @PathVariable Long id,
    //         @RequestParam String firstName,
    //         @RequestParam String lastName) {
    //     Investor user = investorService.createInvestor(id, firstName, lastName);
    //     return new ResponseEntity<>(user, HttpStatus.CREATED);
    // }

    @GetMapping("/{id}/{firstName}/{lastName}")
    // public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    //   return String.format("Hello %s!", name);
    // }
    public ResponseEntity<Investor> createInvestor(
            @PathVariable Long id,
            @PathVariable String firstName,
            @PathVariable String lastName) {
        Investor investor = investorService.createInvestor(id, firstName, lastName);

        if (investor != null){
            return new ResponseEntity<>(investor, HttpStatus.OK);
        }else{
            System.out.println("help");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
    
}
