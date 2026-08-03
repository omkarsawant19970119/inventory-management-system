package com.omkar.inventory.order.dto;

import com.omkar.inventory.order.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderResponse {

    private Long id;

    private Long productId;

    private Integer quantity;

    private Double totalAmount;

    private OrderStatus status;

}