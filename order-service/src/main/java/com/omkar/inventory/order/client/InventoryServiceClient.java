package com.omkar.inventory.order.client;

import com.omkar.inventory.inventory.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryServiceClient {

    @GetMapping("/api/inventory/product/{productId}")
    InventoryResponse getInventory(@PathVariable("productId") Long productId);

    @PutMapping("/api/inventory/reduce-stock/{productId}")
    void reduceStock(@PathVariable("productId") Long productId,
                     @RequestParam("quantity") Integer quantity);
}