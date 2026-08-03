package com.omkar.inventory.supplier.controller;

import com.omkar.inventory.supplier.dto.SupplierRequest;
import com.omkar.inventory.supplier.dto.SupplierResponse;
import com.omkar.inventory.supplier.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierResponse> createSupplier(
            @Valid @RequestBody SupplierRequest request) {

        return new ResponseEntity<>(
                supplierService.createSupplier(request),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getSupplier(
            @PathVariable("id") Long id) {

        return ResponseEntity.ok(
                supplierService.getSupplierById(id));
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAllSuppliers() {

        return ResponseEntity.ok(
                supplierService.getAllSuppliers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponse> updateSupplier(
            @PathVariable("id") Long id,
            @Valid @RequestBody SupplierRequest request) {

        return ResponseEntity.ok(
                supplierService.updateSupplier(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplier(
            @PathVariable("id") Long id) {

        supplierService.deleteSupplier(id);

        return ResponseEntity.ok("Supplier Deleted Successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<SupplierResponse>> searchSupplier(
            @RequestParam("companyName") String companyName) {

        return ResponseEntity.ok(
                supplierService.searchSupplier(companyName));
    }

    @GetMapping("/status/{active}")
    public ResponseEntity<List<SupplierResponse>> getByStatus(
            @PathVariable("active") Boolean active) {

        return ResponseEntity.ok(
                supplierService.getSupplierByStatus(active));
    }
}