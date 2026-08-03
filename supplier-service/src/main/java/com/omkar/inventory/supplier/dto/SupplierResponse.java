package com.omkar.inventory.supplier.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierResponse {

    private Long id;

    private String supplierCode;

    private String companyName;

    private String contactPerson;

    private String email;

    private String phone;

    private String gstNumber;

    private String city;

    private String state;

    private Double rating;

    private Boolean active;
}