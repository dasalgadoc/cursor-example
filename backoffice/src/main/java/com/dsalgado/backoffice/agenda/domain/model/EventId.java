package com.dsalgado.backoffice.agenda.domain.model;

import java.util.UUID;

public final class EventId {
    private final UUID value;

    private EventId(UUID value) {
        this.value = value;
    }

    public static EventId generate() {
        return new EventId(UUID.randomUUID());
    }

    public static EventId fromString(String id) {
        return new EventId(UUID.fromString(id));
    }

    public UUID value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventId eventId = (EventId) o;
        return value.equals(eventId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }
} 