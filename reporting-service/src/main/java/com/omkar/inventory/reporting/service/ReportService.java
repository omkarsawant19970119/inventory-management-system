package com.omkar.inventory.reporting.service;

import com.omkar.inventory.reporting.dto.ReportRequest;
import com.omkar.inventory.reporting.dto.ReportResponse;

import java.util.List;

public interface ReportService {

    ReportResponse createReport(ReportRequest request);

    ReportResponse getReportById(Long id);

    List<ReportResponse> getAllReports();

    ReportResponse updateReport(Long id, ReportRequest request);

    void deleteReport(Long id);
}