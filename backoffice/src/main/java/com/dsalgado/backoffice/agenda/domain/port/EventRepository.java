package com.dsalgado.backoffice.agenda.domain.port;

import com.dsalgado.backoffice.agenda.domain.model.Event;
import com.dsalgado.backoffice.agenda.domain.model.EventDateRange;
import com.dsalgado.backoffice.agenda.domain.model.EventId;

import java.util.List;
import java.util.Optional;

public interface EventRepository {
    Event save(Event event);
    
    Optional<Event> findById(EventId id);
    
    void delete(EventId id);
    
    List<Event> findAllByDateRange(EventDateRange range);
} 