package com.omkar.inventory.inventory.repository;

import com.omkar.inventory.inventory.entity.ProcessedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedEventRepository
        extends JpaRepository<ProcessedEvent,String> {
}