package com.omkar.inventory.common.kafka.events;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreatedEvent {

    private String eventId;

    private Long purchaseId;

    private Long productId;

    private String productName;

    private String skuCode;

    private Double price;
}