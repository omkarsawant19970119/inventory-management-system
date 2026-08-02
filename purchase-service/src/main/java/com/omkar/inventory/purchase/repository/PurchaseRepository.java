package com.omkar.inventory.purchase.repository;

import com.omkar.inventory.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository
        extends JpaRepository<Purchase, Long> {
}