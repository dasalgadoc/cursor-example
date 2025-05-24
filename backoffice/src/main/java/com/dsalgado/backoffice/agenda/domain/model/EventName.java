package com.dsalgado.backoffice.agenda.domain.model;

public final class EventName {
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 100;
    
    private final String value;

    private EventName(String value) {
        validate(value);
        this.value = value;
    }

    public static EventName of(String value) {
        return new EventName(value);
    }

    private void validate(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be null or empty");
        }
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                String.format("Event name must be between %d and %d characters", MIN_LENGTH, MAX_LENGTH)
            );
        }
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventName eventName = (EventName) o;
        return value.equals(eventName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
} 