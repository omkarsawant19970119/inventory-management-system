package com.omkar.inventory.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {

    private Long productId;

    private Integer quantity;

}