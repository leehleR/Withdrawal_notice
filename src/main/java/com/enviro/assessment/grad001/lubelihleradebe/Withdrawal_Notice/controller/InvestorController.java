package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.controller;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Investor;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Product;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.services.InvestorService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



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

    @GetMapping("/{investor_id}/{firstName}/{lastName}")
    public ResponseEntity<Investor> createInvestor(
            @PathVariable Long investor_id,
            @PathVariable String firstName,
            @PathVariable String lastName) {
        Investor investor = investorService.createInvestor(investor_id, firstName, lastName);

        if (investor != null){
            return new ResponseEntity<>(investor, HttpStatus.OK);
        }else{
            System.out.println("help");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 

    @GetMapping("/{investorId}")
    public ResponseEntity<List<Product>> getInvestorWithProducts(@PathVariable long investorId) {
        Investor investor = investorService.getInvestorWithProducts(investorId);
        if (investor != null) {
            List<Product> products = investorService.getInvestorProducts(investorId);
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
