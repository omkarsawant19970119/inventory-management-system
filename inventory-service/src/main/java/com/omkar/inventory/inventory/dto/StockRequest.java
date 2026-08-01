package com.omkar.inventory.inventory.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class StockRequest {

    @Min(value = 1, message = "Quantity must be greater than zero")
    private Integer quantity;

}