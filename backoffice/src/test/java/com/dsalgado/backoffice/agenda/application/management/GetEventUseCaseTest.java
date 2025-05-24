package com.dsalgado.backoffice.agenda.application.management;

import com.dsalgado.backoffice.agenda.application.dto.EventResponse;
import com.dsalgado.backoffice.agenda.domain.model.Event;
import com.dsalgado.backoffice.agenda.domain.model.EventId;
import com.dsalgado.backoffice.agenda.domain.model.EventName;
import com.dsalgado.backoffice.agenda.domain.model.EventDateRange;
import com.dsalgado.backoffice.agenda.domain.model.Location;
import com.dsalgado.backoffice.agenda.domain.port.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GetEventUseCaseTest {
    @Mock
    private EventRepository eventRepository;

    private GetEventUseCase useCase;
    private Event existingEvent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new GetEventUseCase(eventRepository);

        existingEvent = Event.create(
            EventName.of("Test Event"),
            "Test Description",
            EventDateRange.of(LocalDateTime.now(), LocalDateTime.now().plusHours(2)),
            Location.of("Test Location")
        );
    }

    @Test
    void shouldGetEvent() {
        // Given
        String eventId = existingEvent.id().value().toString();
        when(eventRepository.findById(any(EventId.class))).thenReturn(Optional.of(existingEvent));

        // When
        EventResponse response = useCase.execute(eventId);

        // Then
        assertNotNull(response);
        assertEquals(eventId, response.id());
        assertEquals(existingEvent.name().value(), response.name());
        assertEquals(existingEvent.description(), response.description());
        assertEquals(existingEvent.dateRange().startDate(), response.startDate());
        assertEquals(existingEvent.dateRange().endDate(), response.endDate());
        assertEquals(existingEvent.location().value(), response.location());
        assertEquals(existingEvent.isCancelled(), response.cancelled());

        verify(eventRepository).findById(any(EventId.class));
    }

    @Test
    void shouldThrowExceptionWhenEventNotFound() {
        // Given
        String eventId = "non-existent-id";
        when(eventRepository.findById(any(EventId.class))).thenReturn(Optional.empty());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> useCase.execute(eventId));
        verify(eventRepository).findById(any(EventId.class));
    }
} 