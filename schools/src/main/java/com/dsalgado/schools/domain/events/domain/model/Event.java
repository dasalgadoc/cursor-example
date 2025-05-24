package com.dsalgado.schools.domain.events.domain.model;

import com.dsalgado.sharedkernel.domain.model.ValueObject;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Event {
    private final UUID id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String location;
    private boolean active;

    public Event(String title, String description, LocalDateTime startDate, LocalDateTime endDate, String location) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public void updateDetails(String title, String description, LocalDateTime startDate, LocalDateTime endDate, String location) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }
} 