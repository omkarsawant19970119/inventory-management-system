package com.omkar.inventory.purchase.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PurchaseResponse {

    private Long id;

    private Long productId;

    private Integer quantity;

    private Double purchasePrice;

    private String supplierName;

    private LocalDateTime purchaseDate;
}