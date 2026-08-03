package com.omkar.inventory.reporting.service;

import com.omkar.inventory.reporting.dto.ReportRequest;
import com.omkar.inventory.reporting.dto.ReportResponse;
import com.omkar.inventory.reporting.entity.Report;
import com.omkar.inventory.reporting.mapper.ReportMapper;
import com.omkar.inventory.reporting.repository.ReportRepository;
import com.omkar.inventory.reporting.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    @Override
    public ReportResponse createReport(ReportRequest request) {

        Report report = reportMapper.toEntity(request);
        report.setGeneratedDate(LocalDateTime.now());

        return reportMapper.toResponse(reportRepository.save(report));
    }

    @Override
    public ReportResponse getReportById(Long id) {

        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id : " + id));

        return reportMapper.toResponse(report);
    }

    @Override
    public List<ReportResponse> getAllReports() {

        return reportRepository.findAll()
                .stream()
                .map(reportMapper::toResponse)
                .toList();
    }

    @Override
    public ReportResponse updateReport(Long id, ReportRequest request) {

        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id : " + id));

        reportMapper.updateEntity(report, request);

        return reportMapper.toResponse(reportRepository.save(report));
    }

    @Override
    public void deleteReport(Long id) {

        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id : " + id));

        reportRepository.delete(report);
    }
}