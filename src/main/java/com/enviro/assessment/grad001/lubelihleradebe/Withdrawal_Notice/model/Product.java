package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_Id;

    private String type;
    
    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;


    private double amount;
    public Product(){}

    public Product(String type, double amount, Investor investor) {
        this.type = type;
        this.amount = amount;
        this.investor = investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Investor getInvestor() {
        return investor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getProduct_Id() {
        return product_Id;
    }
    public void setProduct_Id(Long product_Id) {
        this.product_Id = product_Id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
}
