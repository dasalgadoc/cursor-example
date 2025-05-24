package com.dsalgado.backoffice.agenda.application.usecase;

import com.dsalgado.backoffice.agenda.application.dto.EventRequest;
import com.dsalgado.backoffice.agenda.application.dto.EventResponse;
import com.dsalgado.backoffice.agenda.domain.event.EventCreated;
import com.dsalgado.backoffice.agenda.domain.model.Event;
import com.dsalgado.backoffice.agenda.domain.model.EventDateRange;
import com.dsalgado.backoffice.agenda.domain.model.EventName;
import com.dsalgado.backoffice.agenda.domain.model.Location;
import com.dsalgado.backoffice.agenda.domain.port.EventPublisher;
import com.dsalgado.backoffice.agenda.domain.port.EventRepository;

public class CreateEventUseCase {
    private final EventRepository eventRepository;
    private final EventPublisher eventPublisher;

    public CreateEventUseCase(EventRepository eventRepository, EventPublisher eventPublisher) {
        this.eventRepository = eventRepository;
        this.eventPublisher = eventPublisher;
    }

    public EventResponse execute(EventRequest request) {
        Event event = Event.create(
            EventName.of(request.name()),
            request.description(),
            EventDateRange.of(request.startDate(), request.endDate()),
            Location.of(request.location())
        );

        event = eventRepository.save(event);
        eventPublisher.publish(EventCreated.from(event));

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