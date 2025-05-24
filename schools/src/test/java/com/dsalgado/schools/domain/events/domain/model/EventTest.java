package com.dsalgado.schools.domain.events.domain.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    @Test
    void shouldCreateEventWithValidData() {
        // Given
        String title = "Test Event";
        String description = "Test Description";
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusHours(2);
        String location = "Test Location";

        // When
        Event event = new Event(title, description, startDate, endDate, location);

        // Then
        assertNotNull(event.getId());
        assertEquals(title, event.getTitle());
        assertEquals(description, event.getDescription());
        assertEquals(startDate, event.getStartDate());
        assertEquals(endDate, event.getEndDate());
        assertEquals(location, event.getLocation());
        assertTrue(event.isActive());
    }

    @Test
    void shouldDeactivateEvent() {
        // Given
        Event event = new Event("Test", "Test", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Test");

        // When
        event.deactivate();

        // Then
        assertFalse(event.isActive());
    }

    @Test
    void shouldActivateEvent() {
        // Given
        Event event = new Event("Test", "Test", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Test");
        event.deactivate();

        // When
        event.activate();

        // Then
        assertTrue(event.isActive());
    }
} 