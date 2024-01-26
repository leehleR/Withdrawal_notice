package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.service;


import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Investor;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Products;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.WithdrawalNotice;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.repository.ProductRepository;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.repository.WithdrawalNoticeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WithdrawalNoticeService {


    private final WithdrawalNoticeRepository withdrawalNoticeRepository;
    private final ProductRepository productRepository;

    private final NotificationService notificationService;

    public WithdrawalNoticeService(WithdrawalNoticeRepository withdrawalNoticeRepository, ProductRepository productRepository, NotificationService notificationService) {
        this.withdrawalNoticeRepository = withdrawalNoticeRepository;
        this.productRepository = productRepository;
        this.notificationService = notificationService;
    }


    @Transactional
    public WithdrawalNotice createWithdrawalNotice(Long productId, double withdrawalAmount){
        Products product  = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        productTypeAgeValidation(product);

        double updateCurrentAmount = product.getCurrentAmount() - withdrawalAmount;
        double withdrawalLimit = 0.9 * product.getCurrentAmount();
        if(withdrawalAmount > updateCurrentAmount || withdrawalAmount > withdrawalLimit){
            throw new IllegalStateException("Withdrawal amount exceeds current balance");
        }


        product.setCurrentAmount(updateCurrentAmount);

        WithdrawalNotice withdrawalNotice = WithdrawalNotice.builder()
                .withdrawalAmount(withdrawalAmount)
                .products(product)
                .investorName(product.getInvestor().getFullName())
                .build();
        WithdrawalNotice savedWithdrawal = withdrawalNoticeRepository.save(withdrawalNotice);

        sendWithdrawalNotification(product, withdrawalAmount, updateCurrentAmount);

        return savedWithdrawal;
    }

    public List<WithdrawalNotice> getWithdrawalNotice(Long productId, Long investorId,LocalDateTime startDate, LocalDateTime endDate){
        return withdrawalNoticeRepository.findByProductsProductIdAndProductsInvestorInvestorIdAndCreatedAtBetween(productId,investorId, startDate,endDate);
    }

    private void productTypeAgeValidation(Products products){
        if("RETIREMENT".equalsIgnoreCase(products.getProductType())){
            Investor investor = products.getInvestor();
            if(investor.getAge() <= 65){
                throw new IllegalStateException("Investor age must be greater than 65");
            }
        }
    }

    private void sendWithdrawalNotification(Products product,
                                            double withdrawalAmount,
                                            double closingBalanced){
        notificationService.sendWithdrawalNotification(
                product.getInvestor(),
                product.getCurrentAmount() + withdrawalAmount,
                withdrawalAmount,
                closingBalanced
        );
    }


}
