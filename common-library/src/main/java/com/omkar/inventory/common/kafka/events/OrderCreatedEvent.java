package com.omkar.inventory.common.kafka.events;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent {

    private Long orderId;

    private Long productId;

    private Integer quantity;

    private Double totalAmount;
}