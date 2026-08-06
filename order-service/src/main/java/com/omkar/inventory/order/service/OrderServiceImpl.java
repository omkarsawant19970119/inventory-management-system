package com.omkar.inventory.order.service;

import com.omkar.inventory.common.exception.ServiceUnavailableException;
import com.omkar.inventory.order.client.InventoryServiceClient;
import com.omkar.inventory.order.client.ProductServiceClient;
import com.omkar.inventory.order.dto.CreateOrderRequest;
import com.omkar.inventory.order.dto.InventoryResponse;
import com.omkar.inventory.order.dto.OrderResponse;
import com.omkar.inventory.order.dto.ProductResponse;
import com.omkar.inventory.order.entity.Order;
import com.omkar.inventory.order.entity.OrderStatus;
import com.omkar.inventory.order.repository.OrderRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import com.omkar.inventory.common.resilience.ResilienceConstants;
import com.omkar.inventory.common.resilience.FallbackMessages;

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
                getProduct(request.getProductId());

        InventoryResponse inventory =
                getInventory(request.getProductId());

        if (inventory.getAvailableQuantity() < request.getQuantity()) {
            throw new RuntimeException("Insufficient Stock");
        }

        reduceStock(
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

    @Bulkhead(
            name = ResilienceConstants.PRODUCT_SERVICE,
            type = Bulkhead.Type.SEMAPHORE,
            fallbackMethod = "productFallback")
    @RateLimiter(
            name = ResilienceConstants.PRODUCT_SERVICE,
            fallbackMethod = "rateLimitingFallback")
    @Retry(
            name = ResilienceConstants.PRODUCT_SERVICE)
    @CircuitBreaker(
            name = ResilienceConstants.PRODUCT_SERVICE,
            fallbackMethod = "productFallback")
    private ProductResponse getProduct(Long productId) {

        return productServiceClient.getProduct(productId);
    }

    @Bulkhead(
            name = ResilienceConstants.INVENTORY_SERVICE,
            type = Bulkhead.Type.SEMAPHORE,
            fallbackMethod = "productFallback")
    @RateLimiter(
            name = ResilienceConstants.INVENTORY_SERVICE,
            fallbackMethod = "rateLimitingFallback")
    @Retry(
            name = ResilienceConstants.INVENTORY_SERVICE)
    @CircuitBreaker(
            name = ResilienceConstants.INVENTORY_SERVICE,
            fallbackMethod = "inventoryFallback")
    private InventoryResponse getInventory(Long productId) {

        return inventoryServiceClient.getInventory(productId);
    }

    @Bulkhead(
            name = ResilienceConstants.INVENTORY_SERVICE,
            type = Bulkhead.Type.SEMAPHORE,
            fallbackMethod = "productFallback")
    @RateLimiter(
            name = ResilienceConstants.INVENTORY_SERVICE,
            fallbackMethod = "rateLimitingFallback")
    @Retry(
            name = ResilienceConstants.INVENTORY_SERVICE)

    @CircuitBreaker(
            name = ResilienceConstants.INVENTORY_SERVICE,
            fallbackMethod = "reduceStockFallback")
    private void reduceStock(Long productId, Integer quantity) {

        inventoryServiceClient.reduceStock(productId, quantity);
    }

    private OrderResponse placeOrderFallback(
            CreateOrderRequest request,
            Throwable ex) {

        throw new ServiceUnavailableException(
                FallbackMessages.PRODUCT_SERVICE_DOWN);
    }

    private OrderResponse rateLimitingFallback(
            CreateOrderRequest request,
            Throwable ex) {

        throw new ServiceUnavailableException(
                FallbackMessages.PRODUCT_SERVICE_DOWN);
    }
}