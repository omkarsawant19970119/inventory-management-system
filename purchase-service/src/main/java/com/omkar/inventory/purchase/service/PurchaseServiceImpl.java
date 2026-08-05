package com.omkar.inventory.purchase.service;

import com.omkar.inventory.common.cache.CacheNames;
import com.omkar.inventory.purchase.client.InventoryServiceClient;
import com.omkar.inventory.purchase.client.ProductServiceClient;
import com.omkar.inventory.purchase.dto.PurchaseRequest;
import com.omkar.inventory.purchase.dto.PurchaseResponse;
import com.omkar.inventory.purchase.entity.Purchase;
import com.omkar.inventory.purchase.repository.PurchaseRepository;
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

        productClient.getProduct(request.getProductId());

        Purchase purchase = Purchase.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .purchasePrice(request.getPurchasePrice())
                .supplierName(request.getSupplierName())
                .purchaseDate(LocalDateTime.now())
                .build();

        repository.save(purchase);

        inventoryClient.addStockFromPurchase(
                request.getProductId(),
                request.getQuantity());

        return map(purchase);
    }

    @Override
    public List<PurchaseResponse> getAllPurchases() {
        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
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