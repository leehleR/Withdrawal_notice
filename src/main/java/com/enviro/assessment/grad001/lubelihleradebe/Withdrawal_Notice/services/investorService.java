package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.services;

import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.print.DocFlavor.STRING;

import org.apache.commons.logging.Log;
import org.slf4j.LoggerFactory;
// import org.hibernate.engine.jdbc.env.internal.LobCreationLogging_.logger;
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

    public Investor createInvestor(long id, String firstName, String lastName){

        // Optional<Investor> existingInvestor = investorRepository.findByIdAndFirstNameAndLastName(id, firstName, lastName);

        // if (existingInvestor.isPresent() ){
        //     Investor investorExists = existingInvestor.get();
        //     return investorExists;
        // }else{
        //     Investor addNewInvestor = addInvestorInfo(id,firstName,lastName);
        //     return addNewInvestor;
        // }

        return investorRepository.findByIdAndFirstNameAndLastName(id, firstName, lastName)
        .orElseGet(() -> {
            // Log.info("not found")
            Investor newInvestor = addInvestorInfo(id, firstName, lastName);
            return investorRepository.save(newInvestor);
        });
       
        
    }

    private Investor addInvestorInfo(long id, String firstName, String lastName){
        Investor investor = new Investor();
        
        investor.setId(id);
        investor.setFirstName(firstName);
        investor.setLastName(lastName);

        Random random = new Random();
        investor.setAge(random.nextInt(80) + 18);
        investor.setAmount(random.nextDouble() * 10000);
        investor.setEmail(firstName.toLowerCase() + "." + lastName.toLowerCase() + "@example.com");
        investor.setContact(contactGenerator());

        return investor;

    }

    private String contactGenerator(){
        Random random = new Random();

        StringBuilder contact = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            contact.append(digit);
        }

        // int randomContact = Integer.parseInt(contact.toString());

        return contact.toString();
    }
}
