package com.omkar.inventory.reporting.controller;

import com.omkar.inventory.reporting.dto.ReportRequest;
import com.omkar.inventory.reporting.dto.ReportResponse;
import com.omkar.inventory.reporting.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ReportResponse createReport(@Valid @RequestBody ReportRequest request) {
        return reportService.createReport(request);
    }

    @GetMapping("/{id}")
    public ReportResponse getReportById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }

    @GetMapping
    public List<ReportResponse> getAllReports() {
        return reportService.getAllReports();
    }

    @PutMapping("/{id}")
    public ReportResponse updateReport(@PathVariable Long id,
                                       @Valid @RequestBody ReportRequest request) {
        return reportService.updateReport(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
    }
}