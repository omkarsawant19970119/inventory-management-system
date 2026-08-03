package com.omkar.inventory.supplier.repository;

import com.omkar.inventory.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Optional<Supplier> findBySupplierCode(String supplierCode);

    List<Supplier> findByCompanyNameContainingIgnoreCase(String companyName);

    List<Supplier> findByActive(Boolean active);
}