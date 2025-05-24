package com.dsalgado.backoffice.agenda.infrastructure.storage;

import com.dsalgado.backoffice.agenda.domain.model.Event;
import com.dsalgado.backoffice.agenda.domain.model.EventDateRange;
import com.dsalgado.backoffice.agenda.domain.model.EventId;
import com.dsalgado.backoffice.agenda.domain.port.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryEventRepository implements EventRepository {
    private final Map<EventId, Event> events = new ConcurrentHashMap<>();

    @Override
    public Event save(Event event) {
        events.put(event.id(), event);
        return event;
    }

    @Override
    public Optional<Event> findById(EventId id) {
        return Optional.ofNullable(events.get(id));
    }

    @Override
    public void delete(EventId id) {
        events.remove(id);
    }

    @Override
    public List<Event> findAllByDateRange(EventDateRange range) {
        return events.values().stream()
            .filter(event -> event.dateRange().overlaps(range))
            .toList();
    }
} 