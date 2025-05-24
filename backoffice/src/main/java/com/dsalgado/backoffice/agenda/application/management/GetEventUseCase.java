package com.dsalgado.backoffice.agenda.application.management;

import com.dsalgado.backoffice.agenda.application.dto.EventResponse;
import com.dsalgado.backoffice.agenda.domain.model.Event;
import com.dsalgado.backoffice.agenda.domain.model.EventId;
import com.dsalgado.backoffice.agenda.domain.port.EventRepository;

public class GetEventUseCase {
    private final EventRepository eventRepository;

    public GetEventUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventResponse execute(String id) {
        Event event = eventRepository.findById(EventId.fromString(id))
            .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        return mapToResponse(event);
    }

    private EventResponse mapToResponse(Event event) {
        return new EventResponse(
            event.id().value().toString(),
            event.name().value(),
            event.description(),
            event.dateRange().startDate(),
            event.dateRange().endDate(),
            event.location().value(),
            event.isCancelled(),
            event.lastModified()
        );
    }
} 