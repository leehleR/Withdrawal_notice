package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private double currentAmount;
    private double newBalance;

    private String productType;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;



    
    public Transaction(Investor investor, String productType, double currentAmount,double newBalance) {
        this.investor = investor;
        this.productType = productType;
        this.currentAmount = currentAmount;
        this.newBalance = currentAmount - newBalance;
    }

    public Transaction(){

    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(double newBalance) {
        this.newBalance = newBalance;
    }


    
}
