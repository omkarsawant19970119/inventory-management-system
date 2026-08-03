package com.omkar.inventory.order.service;

import com.omkar.inventory.order.dto.CreateOrderRequest;
import com.omkar.inventory.order.dto.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(CreateOrderRequest request);

}