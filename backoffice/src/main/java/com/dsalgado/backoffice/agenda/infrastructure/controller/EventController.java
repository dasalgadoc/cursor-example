package com.dsalgado.backoffice.agenda.infrastructure.controller;

import com.dsalgado.backoffice.agenda.application.dto.EventRequest;
import com.dsalgado.backoffice.agenda.application.dto.EventResponse;
import com.dsalgado.backoffice.agenda.application.management.CreateEventUseCase;
import com.dsalgado.backoffice.agenda.application.management.UpdateEventUseCase;
import com.dsalgado.backoffice.agenda.application.management.DeleteEventUseCase;
import com.dsalgado.backoffice.agenda.application.management.GetEventUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final CreateEventUseCase createEventUseCase;
    private final UpdateEventUseCase updateEventUseCase;
    private final DeleteEventUseCase deleteEventUseCase;
    private final GetEventUseCase getEventUseCase;

    public EventController(
            CreateEventUseCase createEventUseCase,
            UpdateEventUseCase updateEventUseCase,
            DeleteEventUseCase deleteEventUseCase,
            GetEventUseCase getEventUseCase) {
        this.createEventUseCase = createEventUseCase;
        this.updateEventUseCase = updateEventUseCase;
        this.deleteEventUseCase = deleteEventUseCase;
        this.getEventUseCase = getEventUseCase;
    }

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest request) {
        EventResponse response = createEventUseCase.execute(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(
            @PathVariable String id,
            @RequestBody EventRequest request) {
        EventResponse response = updateEventUseCase.execute(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        deleteEventUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable String id) {
        EventResponse response = getEventUseCase.execute(id);
        return ResponseEntity.ok(response);
    }
} 