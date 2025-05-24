package com.dsalgado.backoffice.agenda.domain.model;

public final class Location {
    private static final int MIN_LENGTH = 5;
    private final String value;

    private Location(String value) {
        validate(value);
        this.value = value;
    }

    public static Location of(String value) {
        return new Location(value);
    }

    private void validate(String value) {
        if (value != null && value.trim().length() < MIN_LENGTH) {
            throw new IllegalArgumentException(
                String.format("Location must have at least %d characters when provided", MIN_LENGTH)
            );
        }
    }

    public String value() {
        return value;
    }

    public boolean isPresent() {
        return value != null && !value.trim().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return value.equals(location.value);
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