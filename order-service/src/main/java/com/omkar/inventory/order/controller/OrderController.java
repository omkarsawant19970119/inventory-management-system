package com.omkar.inventory.order.controller;

import com.omkar.inventory.order.dto.CreateOrderRequest;
import com.omkar.inventory.order.dto.OrderResponse;
import com.omkar.inventory.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(
            @RequestBody CreateOrderRequest request) {

        return new ResponseEntity<>(
                orderService.placeOrder(request),
                HttpStatus.CREATED);
    }
}