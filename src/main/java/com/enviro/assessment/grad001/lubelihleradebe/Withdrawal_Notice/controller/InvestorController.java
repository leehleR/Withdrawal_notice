package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.controller;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Investor;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Product;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Transaction;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.services.InsufficientFundsException;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.services.InvestorService;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.services.TransactionService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class InvestorController {

    private final InvestorService investorService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    public InvestorController(InvestorService investorService) {
        this.investorService = investorService;
    
    }

    @PostMapping("/transactions")
    public ResponseEntity<?> initiateTransaction(
            @RequestParam Long investorId,
            @RequestParam String productType,
            @RequestParam double sentAmount) {
        try {
            Transaction transaction = transactionService.initialiseTransaction(investorId, productType, sentAmount);
            Investor investor = investorService.getInvestorWithProducts(investorId);

            double currentAmount = investor.getAmount();
            double newBalance = currentAmount - sentAmount;

            String withdrawalNotification = "Transaction Successful! Details: "
            + "New Balance: " + newBalance + ","
            + "Withdrawn Amount: " + sentAmount + ","
            + "Current: " + currentAmount;

            return ResponseEntity.ok(withdrawalNotification);
        } catch (InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

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
