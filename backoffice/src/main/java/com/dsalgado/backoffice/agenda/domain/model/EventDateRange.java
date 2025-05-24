package com.dsalgado.backoffice.agenda.domain.model;

import java.time.LocalDateTime;

public final class EventDateRange {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    private EventDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        validate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static EventDateRange of(LocalDateTime startDate, LocalDateTime endDate) {
        return new EventDateRange(startDate, endDate);
    }

    private void validate(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
    }

    public LocalDateTime startDate() {
        return startDate;
    }

    public LocalDateTime endDate() {
        return endDate;
    }

    public boolean overlaps(EventDateRange other) {
        return !this.endDate.isBefore(other.startDate) && !this.startDate.isAfter(other.endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDateRange that = (EventDateRange) o;
        return startDate.equals(that.startDate) && endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return startDate.hashCode() * 31 + endDate.hashCode();
    }

    @Override
    public String toString() {
        return String.format("EventDateRange{startDate=%s, endDate=%s}", startDate, endDate);
    }
} 