package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "investor")
    private Investor investor;

    private double balanceBeforeWithdrawal;
    private double withdrawalAmount;
    private double closingBalance;
}
