package com.omkar.inventory.purchase.controller;

import com.omkar.inventory.purchase.dto.PurchaseRequest;
import com.omkar.inventory.purchase.dto.PurchaseResponse;
import com.omkar.inventory.purchase.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseResponse create(
            @Valid @RequestBody PurchaseRequest request) {

        return purchaseService.createPurchase(request);
    }

    @GetMapping
    public List<PurchaseResponse> getAll() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/{id}")
    public PurchaseResponse get(@PathVariable("id") Long id) {
        return purchaseService.getPurchase(id);
    }
}