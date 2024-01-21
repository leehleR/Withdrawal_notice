package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.services;

import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.HashSet;
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
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Product;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.repository.InvestorRepository;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.repository.ProductRepository;

@Service
public class InvestorService {
    private final InvestorRepository investorRepository;
    private final ProductRepository productRepository;
   

    @Autowired
    public InvestorService(InvestorRepository investorRepository, ProductRepository productRepository) {
        this.investorRepository = investorRepository;
        this.productRepository = productRepository;
    }

    public List<Product> getInvestorProducts(Long investor_Id) {
        return productRepository.findByInvestor_Id(investor_Id);
    }

    public Investor getInvestorWithProducts(Long investor_Id){
        return investorRepository.findById(investor_Id);
    }

    public Investor createInvestor(long investor_Id, String firstName, String lastName){

        Investor theInvestor = investorRepository.findByIdAndFirstNameAndLastName(investor_Id, firstName, lastName)
        .orElseGet(() -> {

            Investor newInvestor = addInvestorInfo(investor_Id, firstName, lastName);
            newInvestor.generateInitialData();
            Investor savedInvestor = investorRepository.save(newInvestor);

            savedInvestor.generateProducts();
            investorRepository.save(savedInvestor);
            return savedInvestor;
        });

        List<Product> investoProducts = getInvestorProducts(theInvestor.getId());
        theInvestor.setProducts(new HashSet<>(investoProducts));

        return theInvestor;
       
        
    }

    private Investor addInvestorInfo(long investor_Id, String firstName, String lastName){
        Investor investor = new Investor();
        
        investor.setId(investor_Id);
        investor.setFirstName(firstName);
        investor.setLastName(lastName);

        investor.setEmail(firstName.toLowerCase() + "." + lastName.toLowerCase() + "@example.com");
        
        investor.setContact(contactGenerator());

        return investor;

    }

    private String contactGenerator(){
        Random random = new Random();

        StringBuilder contact = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            int digit = random.nextInt(10);
            contact.append(digit);
        }

        return "0"+ contact.toString();
    }
    public void updateInvestor(Investor investor) {
        investorRepository.save(investor);
    }
    
}
