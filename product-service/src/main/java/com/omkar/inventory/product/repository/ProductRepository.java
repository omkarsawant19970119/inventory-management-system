package com.omkar.inventory.product.repository;

import com.omkar.inventory.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsBySkuCode(String skuCode);

    Optional<Product> findBySkuCode(String skuCode);

    List<Product> findByCategory(String category);

    List<Product> findByProductNameContainingIgnoreCase(String keyword);
}