package com.omkar.inventory.supplier.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String supplierCode;

    private String companyName;

    private String contactPerson;

    private String email;

    private String phone;

    private String gstNumber;

    private String panNumber;

    private String address;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private String paymentTerms;

    private Double rating;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}