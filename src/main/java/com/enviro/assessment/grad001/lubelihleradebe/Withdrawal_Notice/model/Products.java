package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Products {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    private String productName;

    private String productType;

    private double currentAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "investor_id")
    private Investor investor;
}
