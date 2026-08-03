package com.omkar.inventory.supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SupplierRequest {

    @NotBlank
    private String supplierCode;

    @NotBlank
    private String companyName;

    private String contactPerson;

    @Email
    private String email;

    private String phone;

    private String gstNumber;

    private String address;

    private String city;

    private String state;

    private String country;

    private String pincode;

    private Boolean active;
}