package com.omkar.inventory.product.service;

import com.omkar.inventory.product.dto.ProductRequest;
import com.omkar.inventory.product.dto.ProductResponse;
import com.omkar.inventory.product.entity.Product;
import com.omkar.inventory.product.exception.DuplicateProductException;
import com.omkar.inventory.product.exception.ProductNotFoundException;
import com.omkar.inventory.product.mapper.ProductMapper;
import com.omkar.inventory.product.repository.ProductRepository;
import com.omkar.inventory.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final Logger log =
            LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository repository;

    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest request) {

        log.info("Creating Product : {}", request.getProductName());

        Product product = productMapper.toEntity(request);

        if(repository.existsBySkuCode(request.getSkuCode())){
            throw new DuplicateProductException(
                    "SKU already exists : " + request.getSkuCode());
        }

        Product saved = repository.save(product);

        log.info("Product Created Successfully. ID={}", product.getId());

        return map(saved);
    }

    @Override
    public ProductResponse updateProduct(Long id,
                                         ProductRequest request) {

        log.info("Updating Product {}", id);

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        productMapper.updateEntity(product, request);

        log.info("Product Updated {}", id);

        return map(repository.save(product));
    }

    @Override
    public ProductResponse getProduct(Long id) {

        log.info("Fetching Product {}", id);

        Product product = repository.findById(id)
                .orElseThrow(() ->{
                    log.warn("Product Not Found : {}", id);
                        return new ProductNotFoundException(
                                "Product not found with id : " + id);});

        return map(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public void deleteProduct(Long id) {

        log.info("Deleting Product {}", id);

        repository.deleteById(id);

        log.info("Product Deleted {}", id);
    }

    private ProductResponse map(Product product) {

        return productMapper.toResponse(product);
    }

    @Override
    public Page<ProductResponse> getProducts(
            int page,
            int size,
            String sortBy,
            String direction) {

        log.info(
                "Fetching Products page={}, size={}",
                page,
                size);

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable)
                .map(productMapper::toResponse);
    }

    @Override
    public List<ProductResponse> searchProducts(String keyword) {

        log.info("Searching Products : {}", keyword);

        return repository
                .findByProductNameContainingIgnoreCase(keyword)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> getProductsByCategory(String category) {

        return repository
                .findByCategory(category)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductBySku(String skuCode) {

        Product product = repository
                .findBySkuCode(skuCode)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with SKU : " + skuCode));

        return productMapper.toResponse(product);
    }
}