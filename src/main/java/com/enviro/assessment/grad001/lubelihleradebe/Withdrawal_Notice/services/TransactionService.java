package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Investor;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Transaction;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.repository.TransactionRepository;

@Service
public class TransactionService {
    
    private final TransactionRepository transactionRepository;

    private final InvestorService investorService;


    @Autowired
    public TransactionService(TransactionRepository transactionRepository, InvestorService investorService){
        this.investorService = investorService;
        this.transactionRepository = transactionRepository;
    }

    public Transaction initialiseTransaction(Long investor_Id, String productType, double sentAmount){
        Investor investor = investorService.getInvestorWithProducts(investor_Id);

        double currentBalance = investor.getAmount();

        if (sentAmount > currentBalance || sentAmount > 1.9 * currentBalance){

           throw new InsufficientFundsException("Insufficient funds for the transaction");
        }

        Transaction transaction = new Transaction();

        transaction.setCurrentAmount(sentAmount);
        transaction.setInvestor(investor);
        transaction.setProductType(productType);

        // Update investor's balance
        investor.setAmount(currentBalance - sentAmount);

        transactionRepository.save(transaction);
        investorService.updateInvestor(investor);;

        return transaction;
    }
}
