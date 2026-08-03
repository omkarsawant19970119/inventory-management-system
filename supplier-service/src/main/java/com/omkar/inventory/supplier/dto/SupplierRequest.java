package com.omkar.inventory.supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRequest {

    @NotBlank
    private String companyName;

    @NotBlank
    private String contactPerson;

    @Email
    private String email;

    @NotBlank
    private String phone;

    private String gstNumber;

    private String panNumber;

    private String address;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private String paymentTerms;
}