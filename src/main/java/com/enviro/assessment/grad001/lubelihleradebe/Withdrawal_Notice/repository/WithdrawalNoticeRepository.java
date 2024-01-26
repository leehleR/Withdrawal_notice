package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.repository;


import com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model.WithdrawalNotice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WithdrawalNoticeRepository extends JpaRepository<WithdrawalNotice, Long> {

    List<WithdrawalNotice> findByProductsProductIdAndProductsInvestorInvestorIdAndCreatedAtBetween(Long productId, Long investorId,LocalDateTime startDate, LocalDateTime endDate);
}
