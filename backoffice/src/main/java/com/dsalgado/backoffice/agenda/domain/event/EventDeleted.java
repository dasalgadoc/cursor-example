package com.dsalgado.backoffice.agenda.domain.event;

import com.dsalgado.backoffice.agenda.domain.model.EventId;

import java.time.LocalDateTime;

public record EventDeleted(
    EventId eventId,
    LocalDateTime occurredOn
) {
    public static EventDeleted from(EventId eventId) {
        return new EventDeleted(eventId, LocalDateTime.now());
    }
} 