package com.omkar.inventory.product.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;

    private String productName;

    private String skuCode;

    private String category;

    private String brand;

    private BigDecimal price;

    private Integer quantity;

    private String unit;

    private String description;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}