package com.omkar.inventory.order.client;

import com.omkar.inventory.order.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductServiceClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getProduct(@PathVariable("id") Long id);

}