package com.omkar.inventory.common.kafka.events;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryUpdatedEvent implements Serializable {

    private Long productId;

    private Integer availableQuantity;

    private Integer reservedQuantity;
}