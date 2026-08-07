package com.omkar.inventory.inventory.dto.event;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseCreatedEvent {

    private Long purchaseId;

    private Long productId;

    private Integer quantity;

    private String supplierName;
}