package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.service;


import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.Products;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.WithdrawalNotice;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.repository.ProductRepository;
import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.repository.WithdrawalNoticeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class WithdrawalNoticeService {


    private final WithdrawalNoticeRepository withdrawalNoticeRepository;
    private final ProductRepository productRepository;

    public WithdrawalNoticeService(WithdrawalNoticeRepository withdrawalNoticeRepository, ProductRepository productRepository) {
        this.withdrawalNoticeRepository = withdrawalNoticeRepository;
        this.productRepository = productRepository;
    }


    @Transactional
    public WithdrawalNotice createWithdrawalNotice(Long productId, double withdrawalAmount){
        Products product  = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        double updateCurrentAmount = product.getCurrentAmount() - withdrawalAmount;

        if(updateCurrentAmount < 0){
            throw new IllegalStateException("Insufficient funds for withdrawal");
        }

        product.setCurrentAmount(updateCurrentAmount);

        WithdrawalNotice withdrawalNotice = WithdrawalNotice.builder()
                .withdrawalAmount(withdrawalAmount)
                .products(product)
                .build();
        return withdrawalNoticeRepository.save(withdrawalNotice);
    }
}
