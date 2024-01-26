package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.service;

import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Investor;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendWithdrawalNotification(Investor investor, double currentBalance, double withdrawalAmount, double closingBalance) {
        System.out.println("Sending withdrawal notification to: " + investor.getFullName());
        System.out.println("Current Balance: " + currentBalance);
        System.out.println("Withdrawal Amount: " + withdrawalAmount);
        System.out.println("Closing Balance: " + closingBalance);
    }
}
