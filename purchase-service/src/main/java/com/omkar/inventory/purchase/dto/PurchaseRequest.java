package com.omkar.inventory.purchase.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PurchaseRequest {

    @NotNull
    private Long productId;

    @Min(1)
    private Integer quantity;

    @NotNull
    private Double purchasePrice;

    @NotNull
    private String supplierName;
}