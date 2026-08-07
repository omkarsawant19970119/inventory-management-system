package com.omkar.inventory.common.kafka.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LowStockEvent {

    private Long productId;

    private Integer availableQuantity;

    private Integer minimumStock;
}
