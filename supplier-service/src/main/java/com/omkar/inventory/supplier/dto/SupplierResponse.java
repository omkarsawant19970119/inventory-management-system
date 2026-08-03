package com.omkar.inventory.supplier.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SupplierResponse {

    private Long id;

    private String supplierCode;

    private String companyName;

    private String contactPerson;

    private String email;

    private String phone;

    private String gstNumber;

    private String address;

    private String city;

    private String state;

    private String country;

    private String pincode;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}