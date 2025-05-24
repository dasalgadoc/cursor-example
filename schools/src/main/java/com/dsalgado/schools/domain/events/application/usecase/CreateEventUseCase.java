package com.dsalgado.schools.domain.events.application.usecase;

import com.dsalgado.schools.domain.events.domain.model.Event;
import com.dsalgado.schools.domain.events.domain.port.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateEventUseCase {
    private final EventRepository eventRepository;

    public Event execute(String title, String description, LocalDateTime startDate, 
                        LocalDateTime endDate, String location) {
        Event event = new Event(title, description, startDate, endDate, location);
        return eventRepository.save(event);
    }
} 