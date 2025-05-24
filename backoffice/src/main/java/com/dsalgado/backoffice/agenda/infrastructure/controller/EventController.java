package com.dsalgado.backoffice.agenda.infrastructure.controller;

import com.dsalgado.backoffice.agenda.application.dto.EventRequest;
import com.dsalgado.backoffice.agenda.application.dto.EventResponse;
import com.dsalgado.backoffice.agenda.application.usecase.CreateEventUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final CreateEventUseCase createEventUseCase;

    public EventController(CreateEventUseCase createEventUseCase) {
        this.createEventUseCase = createEventUseCase;
    }

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest request) {
        EventResponse response = createEventUseCase.execute(request);
        return ResponseEntity.ok(response);
    }
} 