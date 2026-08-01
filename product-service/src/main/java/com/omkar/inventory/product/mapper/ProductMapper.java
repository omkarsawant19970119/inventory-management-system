package com.omkar.inventory.product.mapper;

import com.omkar.inventory.product.dto.ProductRequest;
import com.omkar.inventory.product.dto.ProductResponse;
import com.omkar.inventory.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest request) {

        return Product.builder()
                .productName(request.getProductName())
                .skuCode(request.getSkuCode())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
    }

    public ProductResponse toResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .skuCode(product.getSkuCode())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    public void updateEntity(Product product,
                             ProductRequest request) {

        product.setProductName(request.getProductName());
        product.setSkuCode(request.getSkuCode());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
    }

}