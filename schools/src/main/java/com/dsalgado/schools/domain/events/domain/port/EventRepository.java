package com.dsalgado.schools.domain.events.domain.port;

import com.dsalgado.schools.domain.events.domain.model.Event;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository {
    Event save(Event event);
    Optional<Event> findById(UUID id);
    List<Event> findAll();
    void delete(UUID id);
    boolean existsById(UUID id);
    List<Event> findByDateRange(java.time.LocalDateTime start, java.time.LocalDateTime end);
} 