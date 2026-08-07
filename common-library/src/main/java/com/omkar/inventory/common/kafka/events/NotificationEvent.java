package com.omkar.inventory.common.kafka.events;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEvent implements Serializable {

    private String recipient;

    private String subject;

    private String message;

    private String type;
}