package com.omkar.inventory.order.service;

import com.omkar.inventory.order.client.InventoryServiceClient;
import com.omkar.inventory.order.client.ProductServiceClient;
import com.omkar.inventory.order.dto.CreateOrderRequest;
import com.omkar.inventory.order.dto.InventoryResponse;
import com.omkar.inventory.order.dto.OrderResponse;
import com.omkar.inventory.order.dto.ProductResponse;
import com.omkar.inventory.order.entity.Order;
import com.omkar.inventory.order.entity.OrderStatus;
import com.omkar.inventory.order.repository.OrderRepository;
import com.omkar.inventory.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;
    private final InventoryServiceClient inventoryServiceClient;

    @Override
    public OrderResponse placeOrder(CreateOrderRequest request) {

        ProductResponse product =
                productServiceClient.getProduct(request.getProductId());

        InventoryResponse inventory =
                inventoryServiceClient.getInventory(request.getProductId());

        if (inventory.getAvailableQuantity() < request.getQuantity()) {
            throw new RuntimeException("Insufficient Stock");
        }

        inventoryServiceClient.reduceStock(
                request.getProductId(),
                request.getQuantity());

        Order order = Order.builder()
                .productId(product.getId())
                .quantity(request.getQuantity())
                .price(product.getPrice())
                .totalAmount(product.getPrice() * request.getQuantity())
                .status(OrderStatus.CONFIRMED)
                .orderDate(LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(order);

        return OrderResponse.builder()
                .id(savedOrder.getId())
                .productId(savedOrder.getProductId())
                .quantity(savedOrder.getQuantity())
                .totalAmount(savedOrder.getTotalAmount())
                .status(savedOrder.getStatus())
                .build();
    }
}