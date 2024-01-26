package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.controller;


import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.WithdrawalNotice;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.service.WithdrawalNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enviro/withdrawal-notice")
public class WithdrawalNoticeController {

    private final WithdrawalNoticeService withdrawalNoticeService;


    @Autowired
    public WithdrawalNoticeController(WithdrawalNoticeService withdrawalNoticeService) {
        this.withdrawalNoticeService = withdrawalNoticeService;
    }

    @PostMapping("/make")
    public ResponseEntity<WithdrawalNotice> makeWithdrawalNotice(
            @RequestParam long productId,
            @RequestParam double withdrawalAmount
    ){
        WithdrawalNotice withdrawalNotice = withdrawalNoticeService.createWithdrawalNotice(productId,withdrawalAmount);
        return new ResponseEntity<>(withdrawalNotice, HttpStatus.CREATED);
    }
}
