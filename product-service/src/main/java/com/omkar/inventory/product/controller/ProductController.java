package com.omkar.inventory.product.controller;

import com.omkar.inventory.product.dto.ProductRequest;
import com.omkar.inventory.product.dto.ProductResponse;
import com.omkar.inventory.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(
        name = "Product APIs",
        description = "Operations related to Product Management")

public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Create Product",
            description = "Creates a new product")

    @ApiResponses({

            @ApiResponse(
                    responseCode = "201",
                    description = "Product created successfully"),

            @ApiResponse(
                    responseCode = "400",
                    description = "Validation Error")
    })
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest request) {

        ProductResponse response = productService.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get All Products")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "Get Product By Id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long id) {

        return ResponseEntity.ok(productService.getProduct(id));
    }

    @Operation(summary = "Update Product")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @Operation(summary = "Delete Product")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.ok("Product deleted successfully");
    }

    @Operation(summary = "Pagination and Sorting")
    @GetMapping("/page")
    public ResponseEntity<Page<ProductResponse>> getProducts(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "productName") String sortBy,

            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                productService.getProducts(
                        page,
                        size,
                        sortBy,
                        direction));
    }

    @Operation(summary = "Search Product")
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(

            @RequestParam String keyword) {

        return ResponseEntity.ok(
                productService.searchProducts(keyword));
    }

    @Operation(summary = "Get by Category")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponse>> getByCategory(

            @PathVariable String category) {

        return ResponseEntity.ok(
                productService.getProductsByCategory(category));
    }

    @Operation(summary = "Get by Sku")
    @GetMapping("/sku/{skuCode}")
    public ResponseEntity<ProductResponse> getBySku(

            @PathVariable String skuCode) {

        return ResponseEntity.ok(
                productService.getProductBySku(skuCode));
    }
}