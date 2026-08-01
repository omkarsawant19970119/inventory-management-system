package com.omkar.inventory.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryRequest {

    @NotNull
    private Long productId;

    @Min(0)
    private Integer availableQuantity;

    @Min(0)
    private Integer reservedQuantity;

    @Min(0)
    private Integer minimumStock;

    @Min(0)
    private Integer maximumStock;

    @NotBlank
    private String warehouse;

}