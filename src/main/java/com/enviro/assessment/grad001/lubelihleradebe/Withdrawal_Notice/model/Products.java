package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne
    @JoinColumn(name = "investor_id")
    @JsonBackReference
    private Investor investor;
}
