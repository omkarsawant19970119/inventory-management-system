package com.omkar.inventory.purchase.service;

import com.omkar.inventory.common.cache.CacheNames;
import com.omkar.inventory.common.exception.ServiceUnavailableException;
import com.omkar.inventory.common.resilience.FallbackMessages;
import com.omkar.inventory.common.resilience.ResilienceConstants;
import com.omkar.inventory.order.dto.CreateOrderRequest;
import com.omkar.inventory.order.dto.OrderResponse;
import com.omkar.inventory.purchase.client.InventoryServiceClient;
import com.omkar.inventory.purchase.client.ProductServiceClient;
import com.omkar.inventory.purchase.dto.PurchaseRequest;
import com.omkar.inventory.purchase.dto.PurchaseResponse;
import com.omkar.inventory.purchase.entity.Purchase;
import com.omkar.inventory.purchase.repository.PurchaseRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository repository;
    private final ProductServiceClient productClient;
    private final InventoryServiceClient inventoryClient;

    @Override
    public PurchaseResponse createPurchase(PurchaseRequest request) {

        getProduct(request);

        Purchase purchase = Purchase.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .purchasePrice(request.getPurchasePrice())
                .supplierName(request.getSupplierName())
                .purchaseDate(LocalDateTime.now())
                .build();

        repository.save(purchase);

        addStockFromPurchase(request);

        return map(purchase);
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
            fallbackMethod = "getProductFromProdServiceFallback")
    private void getProduct(PurchaseRequest request){
        inventoryClient.addStockFromPurchase(
                request.getProductId(),
                request.getQuantity());

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
            fallbackMethod = "addStockFromPurchaseFallback")
    private void addStockFromPurchase(PurchaseRequest request){
        inventoryClient.addStockFromPurchase(
                request.getProductId(),
                request.getQuantity());

    }

    @Override
    public List<PurchaseResponse> getAllPurchases() {
        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    private OrderResponse addStockFromPurchaseFallback(
            CreateOrderRequest request,
            Throwable ex) {

        throw new ServiceUnavailableException(
                FallbackMessages.PURCHASE_SERVICE_DOWN);
    }

    private OrderResponse getProductFromProdServiceFallback(
            CreateOrderRequest request,
            Throwable ex) {

        throw new ServiceUnavailableException(
                FallbackMessages.PRODUCT_SERVICE_DOWN);
    }

    @Override
    @Cacheable(value = CacheNames.PURCHASES, key = "#id")
    public PurchaseResponse getPurchase(Long id) {

        Purchase purchase = repository.findById(id)
                .orElseThrow();

        return map(purchase);
    }

    private PurchaseResponse map(Purchase p) {

        return PurchaseResponse.builder()
                .id(p.getId())
                .productId(p.getProductId())
                .quantity(p.getQuantity())
                .purchasePrice(p.getPurchasePrice())
                .supplierName(p.getSupplierName())
                .purchaseDate(p.getPurchaseDate())
                .build();
    }
}