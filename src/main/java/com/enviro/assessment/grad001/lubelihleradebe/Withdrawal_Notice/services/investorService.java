package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Investor;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.repository.InvestorRepository;

@Service
public class InvestorService {
    private final InvestorRepository investorRepository;

    @Autowired
    public InvestorService(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    public Investor createInvestor(long id, String name, String lastname){

        Optional<Investor> existingInvestor = investorRepository.findByIdAndFirstnameAndLastname(id, name, lastname);

        if (existingInvestor.isPresent()){
            Investor investorExists = existingInvestor.get();
            return investorExists;
        }else{
            Investor addNewInvestor = addInvestorInfo(id,name,lastname);
            return addNewInvestor;
        }
       
        
    }

    private Investor addInvestorInfo(long id, String name, String lastname){
        Investor investor = new Investor();
        
        investor.setId(id);
        investor.setFirstName(name);
        // investor.setLastName(lastname);

        Random random = new Random();
        investor.setAge(random.nextInt(80) + 18);
        investor.setAmount(random.nextDouble() * 10000);
        investor.setEmail(name.toLowerCase() + "." + lastname.toLowerCase() + "@example.com");
        investor.setContact(contactGenerator());

        return investor;

    }

    private int contactGenerator(){
        Random random = new Random();

        StringBuilder contact = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            contact.append(digit);
        }

        int randomContact = Integer.parseInt(contact.toString());

        return randomContact;
    }
}
