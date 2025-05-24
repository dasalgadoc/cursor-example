package com.dsalgado.backoffice.agenda.domain.model;

import java.time.LocalDateTime;

public final class Event {
    private final EventId id;
    private EventName name;
    private String description;
    private EventDateRange dateRange;
    private Location location;
    private boolean cancelled;
    private LocalDateTime lastModified;

    private Event(EventId id, EventName name, String description, EventDateRange dateRange, Location location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateRange = dateRange;
        this.location = location;
        this.cancelled = false;
        this.lastModified = LocalDateTime.now();
    }

    public static Event create(EventName name, String description, EventDateRange dateRange, Location location) {
        return new Event(EventId.generate(), name, description, dateRange, location);
    }

    public void update(EventName name, String description, EventDateRange dateRange, Location location) {
        validateUpdate(name, dateRange);
        this.name = name;
        this.description = description;
        this.dateRange = dateRange;
        this.location = location;
        this.lastModified = LocalDateTime.now();
    }

    public void cancel() {
        if (cancelled) {
            throw new IllegalStateException("Event is already cancelled");
        }
        this.cancelled = true;
        this.lastModified = LocalDateTime.now();
    }

    private void validateUpdate(EventName name, EventDateRange dateRange) {
        if (cancelled) {
            throw new IllegalStateException("Cannot update a cancelled event");
        }
        if (name == null || dateRange == null) {
            throw new IllegalArgumentException("Name and date range cannot be null");
        }
    }

    public EventId id() {
        return id;
    }

    public EventName name() {
        return name;
    }

    public String description() {
        return description;
    }

    public EventDateRange dateRange() {
        return dateRange;
    }

    public Location location() {
        return location;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public LocalDateTime lastModified() {
        return lastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id.equals(event.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
} 