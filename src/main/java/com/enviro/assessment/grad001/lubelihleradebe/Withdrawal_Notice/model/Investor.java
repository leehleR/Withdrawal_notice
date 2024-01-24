package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "investor")
public class Investor {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long investorId;

    @Column(unique = true)
    private String fullName;

    private int age;

    private String gender;

    @Embedded
    private Contact contact;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Products> productsList;

//    @ElementCollection
//    @CollectionTable(name = "investor_product_types", joinColumns = @JoinColumn(name = "investor_id"))
//    @Column(name = "product_type")
//    private List<String> productTypes;


    @Embeddable
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Contact{
        private String phone;
        private String email;


    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Address{
        private String street;
        private String city;
    }




}
