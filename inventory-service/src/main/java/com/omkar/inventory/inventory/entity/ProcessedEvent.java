package com.omkar.inventory.inventory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "processed_events")
@Getter
@Setter
public class ProcessedEvent {

    @Id
    private String eventId;

    private LocalDateTime processedAt;
}