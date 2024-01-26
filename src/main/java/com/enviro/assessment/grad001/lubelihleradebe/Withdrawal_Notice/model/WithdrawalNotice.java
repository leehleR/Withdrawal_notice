package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WithdrawalNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double withdrawalAmount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;
}
