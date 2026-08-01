package com.omkar.inventory.inventory.controller;

import com.omkar.inventory.inventory.dto.InventoryRequest;
import com.omkar.inventory.inventory.dto.InventoryResponse;
import com.omkar.inventory.inventory.dto.StockRequest;
import com.omkar.inventory.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponse> createInventory(
            @Valid @RequestBody InventoryRequest request) {

        InventoryResponse response = inventoryService.createInventory(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventory() {

        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getInventoryById(
            @PathVariable("id") Long id) {

        return ResponseEntity.ok(
                inventoryService.getInventoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> updateInventory(
            @PathVariable ("id")Long id,
            @Valid @RequestBody InventoryRequest request) {

        return ResponseEntity.ok(
                inventoryService.updateInventory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventory(
            @PathVariable ("id")Long id) {

        inventoryService.deleteInventory(id);

        return ResponseEntity.ok("Inventory deleted successfully");
    }

    @PutMapping("/add-stock/{productId}")
    public ResponseEntity<InventoryResponse> addStock(
            @PathVariable("productId") Long productId,
            @Valid @RequestBody StockRequest request) {

        return ResponseEntity.ok(
                inventoryService.addStock(productId, request.getQuantity())
        );
    }

    @PutMapping("/remove-stock/{productId}")
    public ResponseEntity<InventoryResponse> removeStock(
            @PathVariable("productId") Long productId,
            @Valid @RequestBody StockRequest request) {

        return ResponseEntity.ok(
                inventoryService.removeStock(productId, request.getQuantity())
        );
    }

    @PutMapping("/reserve-stock/{productId}")
    public ResponseEntity<InventoryResponse> reserveStock(
            @PathVariable("productId") Long productId,
            @Valid @RequestBody StockRequest request) {

        return ResponseEntity.ok(
                inventoryService.reserveStock(productId, request.getQuantity())
        );
    }

    @PutMapping("/release-stock/{productId}")
    public ResponseEntity<InventoryResponse> releaseStock(
            @PathVariable("productId") Long productId,
            @Valid @RequestBody StockRequest request) {

        return ResponseEntity.ok(
                inventoryService.releaseStock(productId, request.getQuantity())
        );
    }
}