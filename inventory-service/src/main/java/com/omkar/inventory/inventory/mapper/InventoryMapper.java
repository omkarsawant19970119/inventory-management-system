package com.omkar.inventory.inventory.mapper;

import com.omkar.inventory.inventory.dto.InventoryRequest;
import com.omkar.inventory.inventory.dto.InventoryResponse;
import com.omkar.inventory.inventory.entity.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public Inventory toEntity(InventoryRequest request) {

        return Inventory.builder()
                .productId(request.getProductId())
                .availableQuantity(request.getAvailableQuantity())
                .reservedQuantity(request.getReservedQuantity())
                .minimumStock(request.getMinimumStock())
                .maximumStock(request.getMaximumStock())
                .warehouse(request.getWarehouse())
                .build();
    }

    public InventoryResponse toResponse(Inventory inventory) {

        return InventoryResponse.builder()
                .id(inventory.getId())
                .productId(inventory.getProductId())
                .availableQuantity(inventory.getAvailableQuantity())
                .reservedQuantity(inventory.getReservedQuantity())
                .minimumStock(inventory.getMinimumStock())
                .maximumStock(inventory.getMaximumStock())
                .warehouse(inventory.getWarehouse())
                .createdAt(inventory.getCreatedAt())
                .updatedAt(inventory.getUpdatedAt())
                .build();
    }
}