package com.enviro.assessment.grad001.lubelihleradebe.Withdrawal_Notice.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ElementCollection
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "investor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Products> productsList;


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
