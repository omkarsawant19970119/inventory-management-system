package com.omkar.inventory.inventory.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {

    private Long id;

    private String skuCode;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    private String category;
}