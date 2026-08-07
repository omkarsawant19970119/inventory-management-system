package com.omkar.inventory.inventory.service;

import com.omkar.inventory.inventory.entity.ProcessedEvent;
import com.omkar.inventory.inventory.repository.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventProcessingService {

    private final ProcessedEventRepository repository;

    public boolean isAlreadyProcessed(String eventId){

        return repository.existsById(eventId);

    }

    public void markProcessed(String eventId){

        ProcessedEvent event = new ProcessedEvent();

        event.setEventId(eventId);

        event.setProcessedAt(LocalDateTime.now());

        repository.save(event);

    }

}