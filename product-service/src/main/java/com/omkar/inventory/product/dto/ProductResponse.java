package com.omkar.inventory.product.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;

    private String sku;

    private String name;

    private String description;

    private String category;

    private String brand;

    private BigDecimal price;

    private Integer quantity;

    private String status;

}