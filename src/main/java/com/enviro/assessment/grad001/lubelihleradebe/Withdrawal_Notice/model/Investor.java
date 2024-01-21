package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
// import java.util.List;



@Entity
public class Investor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long investor_id;

    private String firstName;
    private String lastName;
    private String email;
    // add address
    private String contact;
    private int age;
    private double amount;

    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL, orphanRemoval = true)
    @Autowired
    Set<Product> products = new HashSet<>();

    public void generateInitialData() {
        this.age = investorAge();
        this.amount = moneyInvested();
    }


    

    public long getId() {
        return investor_id;
    }

    public void setId(long id) {
        this.investor_id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public int investorAge() {
        Random random = new Random();
        return age = random.nextInt(63) + 18;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public double moneyInvested() {
        Random random = new Random();

        // generate the amount the investor has
        
        return Math.round(amount = random.nextDouble() * 10000);
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getAmount() {
        return amount;
    }



    // to generate amount to the investors randomly

    public void generateProducts(){
        

        if (age >= 65){
            addProduct("Retirement", amount);
        }else if (age > 40 && age < 65) {
            double retirementAmount = amount * 0.3;
            double savingsAmount = amount - retirementAmount;

            addProduct("Savings", savingsAmount);
            addProduct("Retirement", retirementAmount);
        }else{
            addProduct("Savings", amount);
        }

    }

    public void addProduct(String type, double amount){
        Product product = new Product(type,amount,this);
        products.add(product);
        product.setInvestor(this);
    }
    public void setProducts(Set<Product> products) {
        this.products = products;
    }


    
}
