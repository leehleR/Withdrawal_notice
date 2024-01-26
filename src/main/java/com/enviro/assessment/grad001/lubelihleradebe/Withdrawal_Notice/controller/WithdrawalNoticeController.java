package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.controller;


import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.WithdrawalNotice;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.service.NotificationService;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.service.WithdrawalNoticeService;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.service.extra.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/enviro/withdrawal-notice")
public class WithdrawalNoticeController {

    private final WithdrawalNoticeService withdrawalNoticeService;
    private final NotificationService notificationService;


    @Autowired
    public WithdrawalNoticeController(WithdrawalNoticeService withdrawalNoticeService, NotificationService notificationService) {
        this.withdrawalNoticeService = withdrawalNoticeService;
        this.notificationService = notificationService;
    }

    @PostMapping("/make")
    public ResponseEntity<WithdrawalNotice> makeWithdrawalNotice(
            @RequestParam long productId,
            @RequestParam double withdrawalAmount
    ){
        WithdrawalNotice withdrawalNotice = withdrawalNoticeService
                .createWithdrawalNotice(productId,withdrawalAmount);
        return new ResponseEntity<>(withdrawalNotice, HttpStatus.CREATED);
    }

    @GetMapping("notice/download")
    public ResponseEntity<byte[]> csvNotices(
            @RequestParam Long productId,
            @RequestParam Long investorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
            ){
        List<WithdrawalNotice> notices = withdrawalNoticeService
                .getWithdrawalNotice(productId,investorId, startDate,endDate);

        String csvContent = CsvUtil.convertNoticeToCsv(notices);

        byte[] csvBytes = csvContent.getBytes(StandardCharsets.UTF_8);

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", "notices.csv");

        return new ResponseEntity<>(csvBytes,httpHeaders,HttpStatus.OK);
    }
}
