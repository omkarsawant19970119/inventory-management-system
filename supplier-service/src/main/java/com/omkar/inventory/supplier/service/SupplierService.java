package com.omkar.inventory.supplier.service;

import com.omkar.inventory.supplier.dto.SupplierRequest;
import com.omkar.inventory.supplier.dto.SupplierResponse;

import java.util.List;

public interface SupplierService {

    SupplierResponse createSupplier(SupplierRequest request);

    SupplierResponse getSupplierById(Long id);

    List<SupplierResponse> getAllSuppliers();

    SupplierResponse updateSupplier(Long id, SupplierRequest request);

    void deleteSupplier(Long id);

    List<SupplierResponse> searchSupplier(String companyName);

    List<SupplierResponse> getSupplierByStatus(Boolean active);

}