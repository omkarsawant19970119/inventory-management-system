package com.omkar.inventory.purchase.service;

import com.omkar.inventory.purchase.dto.PurchaseRequest;
import com.omkar.inventory.purchase.dto.PurchaseResponse;

import java.util.List;

public interface PurchaseService {

    PurchaseResponse createPurchase(PurchaseRequest request);

    List<PurchaseResponse> getAllPurchases();

    PurchaseResponse getPurchase(Long id);
}