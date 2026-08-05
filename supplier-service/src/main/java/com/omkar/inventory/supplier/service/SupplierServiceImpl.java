package com.omkar.inventory.supplier.service;

import com.omkar.inventory.common.cache.CacheNames;
import com.omkar.inventory.supplier.dto.SupplierRequest;
import com.omkar.inventory.supplier.dto.SupplierResponse;
import com.omkar.inventory.supplier.entity.Supplier;
import com.omkar.inventory.supplier.repository.SupplierRepository;
import com.omkar.inventory.supplier.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public SupplierResponse createSupplier(SupplierRequest request) {

        Supplier supplier = mapToEntity(request);

        return mapToResponse(
                supplierRepository.save(supplier)
        );
    }

    @Override
    @Cacheable(value = CacheNames.SUPPLIERS, key = "#id")
    public SupplierResponse getSupplierById(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Supplier not found"));

        return mapToResponse(supplier);
    }

    @Override
    public List<SupplierResponse> getAllSuppliers() {

        return supplierRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @CachePut(value = CacheNames.SUPPLIERS, key = "#id")
    public SupplierResponse updateSupplier(Long id,
                                           SupplierRequest request) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Supplier not found"));

        supplier.setSupplierCode(request.getSupplierCode());
        supplier.setCompanyName(request.getCompanyName());
        supplier.setContactPerson(request.getContactPerson());
        supplier.setEmail(request.getEmail());
        supplier.setPhone(request.getPhone());
        supplier.setGstNumber(request.getGstNumber());
        supplier.setAddress(request.getAddress());
        supplier.setCity(request.getCity());
        supplier.setState(request.getState());
        supplier.setCountry(request.getCountry());
        supplier.setPincode(request.getPincode());
        supplier.setActive(request.getActive());

        return mapToResponse(
                supplierRepository.save(supplier)
        );
    }

    @Override
    @CacheEvict(value = CacheNames.SUPPLIERS, key = "#id")
    public void deleteSupplier(Long id) {

        supplierRepository.deleteById(id);
    }

    @Override
    public List<SupplierResponse> searchSupplier(String companyName) {

        return supplierRepository
                .findByCompanyNameContainingIgnoreCase(companyName)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Cacheable(
            value = CacheNames.ACTIVE_SUPPLIERS,
            key = "#active"
    )
    public List<SupplierResponse> getSupplierByStatus(Boolean active) {

        return supplierRepository.findByActive(active)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private Supplier mapToEntity(SupplierRequest request){

        return Supplier.builder()
                .supplierCode(request.getSupplierCode())
                .companyName(request.getCompanyName())
                .contactPerson(request.getContactPerson())
                .email(request.getEmail())
                .phone(request.getPhone())
                .gstNumber(request.getGstNumber())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .pincode(request.getPincode())
                .active(request.getActive())
                .build();
    }

    private SupplierResponse mapToResponse(Supplier supplier){

        return SupplierResponse.builder()
                .id(supplier.getId())
                .supplierCode(supplier.getSupplierCode())
                .companyName(supplier.getCompanyName())
                .contactPerson(supplier.getContactPerson())
                .email(supplier.getEmail())
                .phone(supplier.getPhone())
                .gstNumber(supplier.getGstNumber())
                .address(supplier.getAddress())
                .city(supplier.getCity())
                .state(supplier.getState())
                .country(supplier.getCountry())
                .pincode(supplier.getPincode())
                .active(supplier.getActive())
                .createdAt(supplier.getCreatedAt())
                .updatedAt(supplier.getUpdatedAt())
                .build();
    }
}