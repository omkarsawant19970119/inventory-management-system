package com.omkar.inventory.purchase.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryServiceClient {

    @PutMapping("/api/inventory/add-stock-from-purchase/{productId}")
    void addStockFromPurchase(@PathVariable("productId") Long productId,
                  @RequestParam("quantity") Integer quantity);

}