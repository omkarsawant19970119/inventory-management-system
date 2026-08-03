package com.omkar.inventory.reporting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse {

    private Long id;

    private String reportName;

    private String reportType;

    private String generatedBy;

    private LocalDateTime generatedDate;

    private String status;
}