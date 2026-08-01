package com.omkar.inventory.product.service;

import com.omkar.inventory.product.dto.ProductRequest;
import com.omkar.inventory.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    ProductResponse updateProduct(Long id, ProductRequest request);

    ProductResponse getProduct(Long id);

    List<ProductResponse> getAllProducts();

    void deleteProduct(Long id);

    Page<ProductResponse> getProducts(
            int page,
            int size,
            String sortBy,
            String direction);

    List<ProductResponse> searchProducts(String keyword);

    List<ProductResponse> getProductsByCategory(String category);

    ProductResponse getProductBySku(String skuCode);
}