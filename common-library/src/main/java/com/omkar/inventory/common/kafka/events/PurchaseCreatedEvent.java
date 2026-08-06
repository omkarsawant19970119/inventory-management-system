package com.omkar.inventory.common.kafka.events;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseCreatedEvent {

    private String eventId;

    private Long purchaseId;

    private Long productId;

    private Integer quantity;

    private Double purchasePrice;

    private String supplierName;
}
