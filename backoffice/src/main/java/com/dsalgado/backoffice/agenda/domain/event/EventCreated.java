package com.dsalgado.backoffice.agenda.domain.event;

import com.dsalgado.backoffice.agenda.domain.model.Event;
import com.dsalgado.backoffice.agenda.domain.model.EventId;

import java.time.LocalDateTime;

public record EventCreated(
    EventId eventId,
    String name,
    LocalDateTime startDate,
    LocalDateTime endDate,
    LocalDateTime occurredOn
) {
    public static EventCreated from(Event event) {
        return new EventCreated(
            event.id(),
            event.name().value(),
            event.dateRange().startDate(),
            event.dateRange().endDate(),
            LocalDateTime.now()
        );
    }
} 