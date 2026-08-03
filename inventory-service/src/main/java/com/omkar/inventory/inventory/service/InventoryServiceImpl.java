package com.omkar.inventory.inventory.service;

import com.omkar.inventory.inventory.client.ProductServiceClient;
import com.omkar.inventory.inventory.dto.InventoryRequest;
import com.omkar.inventory.inventory.dto.InventoryResponse;
import com.omkar.inventory.inventory.dto.ProductResponse;
import com.omkar.inventory.inventory.entity.Inventory;
import com.omkar.inventory.inventory.exception.InsufficientStockException;
import com.omkar.inventory.inventory.exception.InvalidStockOperationException;
import com.omkar.inventory.inventory.exception.InventoryNotFoundException;
import com.omkar.inventory.inventory.mapper.InventoryMapper;
import com.omkar.inventory.inventory.repository.InventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final ProductServiceClient productServiceClient;

    @Override
    public InventoryResponse createInventory(InventoryRequest request) {
        ProductResponse product =
                productServiceClient.getProductById(request.getProductId());

        if (product == null) {
            throw new InventoryNotFoundException("Product not found");
        }
        Inventory inventory = inventoryMapper.toEntity(request);

        if (inventoryRepository.findByProductId(request.getProductId()).isPresent()) {
            throw new RuntimeException("Inventory already exists for this product");
        }

        inventoryRepository.save(inventory);

        Inventory savedInventory = inventoryRepository.save(inventory);

        return inventoryMapper.toResponse(savedInventory);
    }

    @Override
    public List<InventoryResponse> getAllInventory() {

        return inventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::toResponse)
                .toList();
    }

    @Override
    public InventoryResponse getInventoryById(Long id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Inventory not found with id : " + id));

        return inventoryMapper.toResponse(inventory);
    }

    @Override
    public InventoryResponse updateInventory(Long id,
                                             InventoryRequest request) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Inventory not found with id : " + id));

        inventory.setProductId(request.getProductId());
        inventory.setAvailableQuantity(request.getAvailableQuantity());
        inventory.setReservedQuantity(request.getReservedQuantity());
        inventory.setMinimumStock(request.getMinimumStock());
        inventory.setMaximumStock(request.getMaximumStock());
        inventory.setWarehouse(request.getWarehouse());

        Inventory updatedInventory = inventoryRepository.save(inventory);

        return inventoryMapper.toResponse(updatedInventory);
    }

    @Override
    public void deleteInventory(Long id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Inventory not found with id : " + id));

        inventoryRepository.delete(inventory);
    }

    @Override
    public InventoryResponse addStock(Long productId, Integer quantity) {

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException(
                        "Inventory not found for Product Id : " + productId));

        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity() + quantity
        );

        Inventory saved = inventoryRepository.save(inventory);

        return inventoryMapper.toResponse(saved);
    }

    @Override
    public InventoryResponse removeStock(Long productId, Integer quantity) {

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException(
                "Inventory not found for Product Id : " + productId));

        if (inventory.getAvailableQuantity() < quantity) {
            throw new InsufficientStockException(
                    "Only " + inventory.getAvailableQuantity() + " items available.");
        }

        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity() - quantity
        );

        Inventory saved = inventoryRepository.save(inventory);

        return inventoryMapper.toResponse(saved);
    }

    @Override
    public InventoryResponse reserveStock(Long productId, Integer quantity) {

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException(
                "Inventory not found for Product Id : " + productId));

        if (inventory.getAvailableQuantity() < quantity) {
            throw new InsufficientStockException(
                    "Only " + inventory.getAvailableQuantity() + " items available.");
        }

        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity() - quantity
        );

        inventory.setReservedQuantity(
                inventory.getReservedQuantity() + quantity
        );

        Inventory saved = inventoryRepository.save(inventory);

        return inventoryMapper.toResponse(saved);
    }

    @Override
    public InventoryResponse releaseStock(Long productId, Integer quantity) {

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException(
                "Inventory not found for Product Id : " + productId));

        if (inventory.getReservedQuantity() < quantity) {
            throw new InvalidStockOperationException(
                    "Reserved quantity is less than requested quantity.");
        }

        inventory.setReservedQuantity(
                inventory.getReservedQuantity() - quantity
        );

        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity() + quantity
        );

        Inventory saved = inventoryRepository.save(inventory);

        return inventoryMapper.toResponse(saved);
    }

    @Override
    public void addStockFromPurchase(Long productId, Integer quantity) {

        Inventory inventory = inventoryRepository
                .findByProductId(productId)
                .orElseThrow(() ->
                        new RuntimeException("Inventory not found"));

        inventory.setAvailableQuantity(inventory.getAvailableQuantity() + quantity);

        inventoryRepository.save(inventory);
    }

    @Override
    public InventoryResponse getInventory(Long productId) {

        Inventory inventory = inventoryRepository
                .findByProductId(productId)
                .orElseThrow(() ->
                        new RuntimeException("Inventory not found"));

        InventoryResponse response = new InventoryResponse();

        response.setProductId(inventory.getProductId());
        response.setAvailableQuantity(inventory.getAvailableQuantity());

        return response;
    }

    @Override
    public void reduceStock(Long productId, Integer quantity) {

        Inventory inventory = inventoryRepository
                .findByProductId(productId)
                .orElseThrow(() ->
                        new RuntimeException("Inventory not found"));

        if (inventory.getAvailableQuantity() < quantity) {
            throw new RuntimeException("Insufficient Stock");
        }

        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity() - quantity);

        inventoryRepository.save(inventory);
    }
}