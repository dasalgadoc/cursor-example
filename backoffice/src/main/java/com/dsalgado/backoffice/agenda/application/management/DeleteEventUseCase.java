package com.dsalgado.backoffice.agenda.application.management;

import com.dsalgado.backoffice.agenda.domain.event.EventDeleted;
import com.dsalgado.backoffice.agenda.domain.model.EventId;
import com.dsalgado.backoffice.agenda.domain.port.EventPublisher;
import com.dsalgado.backoffice.agenda.domain.port.EventRepository;

public class DeleteEventUseCase {
    private final EventRepository eventRepository;
    private final EventPublisher eventPublisher;

    public DeleteEventUseCase(EventRepository eventRepository, EventPublisher eventPublisher) {
        this.eventRepository = eventRepository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(String id) {
        EventId eventId = EventId.fromString(id);
        if (!eventRepository.findById(eventId).isPresent()) {
            throw new IllegalArgumentException("Event not found");
        }

        eventRepository.delete(eventId);
        eventPublisher.publish(EventDeleted.from(eventId));
    }
} 