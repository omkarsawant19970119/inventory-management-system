package com.omkar.inventory.inventory.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {

    private Long id;

    private Long productId;

    private Integer availableQuantity;

    private Integer reservedQuantity;

    private Integer minimumStock;

    private Integer maximumStock;

    private String warehouse;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}