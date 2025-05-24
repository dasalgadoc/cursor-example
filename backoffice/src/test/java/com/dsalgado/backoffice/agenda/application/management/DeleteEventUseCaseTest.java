package com.dsalgado.backoffice.agenda.application.management;

import com.dsalgado.backoffice.agenda.domain.event.EventDeleted;
import com.dsalgado.backoffice.agenda.domain.model.Event;
import com.dsalgado.backoffice.agenda.domain.model.EventId;
import com.dsalgado.backoffice.agenda.domain.model.EventName;
import com.dsalgado.backoffice.agenda.domain.model.EventDateRange;
import com.dsalgado.backoffice.agenda.domain.model.Location;
import com.dsalgado.backoffice.agenda.domain.port.EventPublisher;
import com.dsalgado.backoffice.agenda.domain.port.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeleteEventUseCaseTest {
    @Mock
    private EventRepository eventRepository;
    @Mock
    private EventPublisher eventPublisher;
    @Captor
    private ArgumentCaptor<EventDeleted> eventCaptor;

    private DeleteEventUseCase useCase;
    private Event existingEvent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new DeleteEventUseCase(eventRepository, eventPublisher);

        existingEvent = Event.create(
            EventName.of("Test Event"),
            "Test Description",
            EventDateRange.of(LocalDateTime.now(), LocalDateTime.now().plusHours(2)),
            Location.of("Test Location")
        );
    }

    @Test
    void shouldDeleteEvent() {
        // Given
        String eventId = existingEvent.id().value().toString();
        when(eventRepository.findById(any(EventId.class))).thenReturn(Optional.of(existingEvent));

        // When
        useCase.execute(eventId);

        // Then
        verify(eventRepository).findById(any(EventId.class));
        verify(eventRepository).delete(any(EventId.class));
        verify(eventPublisher).publish(eventCaptor.capture());

        EventDeleted publishedEvent = eventCaptor.getValue();
        assertEquals(eventId, publishedEvent.eventId().value().toString());
    }

    @Test
    void shouldThrowExceptionWhenEventNotFound() {
        // Given
        String eventId = "non-existent-id";
        when(eventRepository.findById(any(EventId.class))).thenReturn(Optional.empty());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> useCase.execute(eventId));
        verify(eventRepository).findById(any(EventId.class));
        verify(eventRepository, never()).delete(any(EventId.class));
        verify(eventPublisher, never()).publish(any(EventDeleted.class));
    }
} 