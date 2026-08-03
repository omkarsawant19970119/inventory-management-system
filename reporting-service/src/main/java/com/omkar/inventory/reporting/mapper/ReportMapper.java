package com.omkar.inventory.reporting.mapper;

import com.omkar.inventory.reporting.dto.ReportRequest;
import com.omkar.inventory.reporting.dto.ReportResponse;
import com.omkar.inventory.reporting.entity.Report;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    public Report toEntity(ReportRequest request) {

        return Report.builder()
                .reportName(request.getReportName())
                .reportType(request.getReportType())
                .generatedBy(request.getGeneratedBy())
                .generatedDate(request.getGeneratedDate())
                .status(request.getStatus())
                .build();
    }

    public ReportResponse toResponse(Report report) {

        return ReportResponse.builder()
                .id(report.getId())
                .reportName(report.getReportName())
                .reportType(report.getReportType())
                .generatedBy(report.getGeneratedBy())
                .generatedDate(report.getGeneratedDate())
                .status(report.getStatus())
                .build();
    }

    public void updateEntity(Report report, ReportRequest request) {

        report.setReportName(request.getReportName());
        report.setReportType(request.getReportType());
        report.setGeneratedBy(request.getGeneratedBy());
        report.setGeneratedDate(request.getGeneratedDate());
        report.setStatus(request.getStatus());
    }
}