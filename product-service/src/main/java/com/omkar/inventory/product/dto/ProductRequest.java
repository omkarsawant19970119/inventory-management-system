package com.omkar.inventory.product.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank
    private String sku;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String category;

    @NotBlank
    private String brand;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

}