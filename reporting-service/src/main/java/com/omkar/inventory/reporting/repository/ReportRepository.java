package com.omkar.inventory.reporting.repository;

import com.omkar.inventory.reporting.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}