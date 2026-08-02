package com.omkar.inventory.inventory.service;

import com.omkar.inventory.inventory.dto.InventoryRequest;
import com.omkar.inventory.inventory.dto.InventoryResponse;

import java.util.List;


public interface InventoryService {

    InventoryResponse createInventory(InventoryRequest request);

    List<InventoryResponse> getAllInventory();

    InventoryResponse getInventoryById(Long id);

    InventoryResponse updateInventory(Long id, InventoryRequest request);

    void deleteInventory(Long id);

    InventoryResponse addStock(Long productId, Integer quantity);

    InventoryResponse removeStock(Long productId, Integer quantity);

    InventoryResponse reserveStock(Long productId, Integer quantity);

    InventoryResponse releaseStock(Long productId, Integer quantity);

    void addStockFromPurchase(Long productId, Integer quantity);
}