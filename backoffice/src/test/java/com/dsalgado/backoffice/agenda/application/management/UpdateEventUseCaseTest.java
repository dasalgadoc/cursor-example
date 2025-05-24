package com.dsalgado.backoffice.agenda.application.management;

import com.dsalgado.backoffice.agenda.application.dto.EventRequest;
import com.dsalgado.backoffice.agenda.application.dto.EventResponse;
import com.dsalgado.backoffice.agenda.domain.event.EventUpdated;
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

class UpdateEventUseCaseTest {
    @Mock
    private EventRepository eventRepository;
    @Mock
    private EventPublisher eventPublisher;
    @Captor
    private ArgumentCaptor<EventUpdated> eventCaptor;

    private UpdateEventUseCase useCase;
    private Event existingEvent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new UpdateEventUseCase(eventRepository, eventPublisher);

        existingEvent = Event.create(
            EventName.of("Original Event"),
            "Original Description",
            EventDateRange.of(LocalDateTime.now(), LocalDateTime.now().plusHours(2)),
            Location.of("Original Location")
        );
    }

    @Test
    void shouldUpdateEvent() {
        // Given
        String eventId = existingEvent.id().value().toString();
        EventRequest request = new EventRequest(
            "Updated Event",
            "Updated Description",
            LocalDateTime.now().plusDays(1),
            LocalDateTime.now().plusDays(1).plusHours(2),
            "Updated Location"
        );

        when(eventRepository.findById(any(EventId.class))).thenReturn(Optional.of(existingEvent));
        when(eventRepository.save(any(Event.class))).thenAnswer(i -> i.getArgument(0));

        // When
        EventResponse response = useCase.execute(eventId, request);

        // Then
        assertNotNull(response);
        assertEquals(request.name(), response.name());
        assertEquals(request.description(), response.description());
        assertEquals(request.startDate(), response.startDate());
        assertEquals(request.endDate(), response.endDate());
        assertEquals(request.location(), response.location());
        assertFalse(response.cancelled());

        verify(eventRepository).findById(any(EventId.class));
        verify(eventRepository).save(any(Event.class));
        verify(eventPublisher).publish(eventCaptor.capture());

        EventUpdated publishedEvent = eventCaptor.getValue();
        assertEquals(request.name(), publishedEvent.name());
        assertEquals(request.startDate(), publishedEvent.startDate());
        assertEquals(request.endDate(), publishedEvent.endDate());
    }

    @Test
    void shouldThrowExceptionWhenEventNotFound() {
        // Given
        String eventId = "non-existent-id";
        EventRequest request = new EventRequest(
            "Updated Event",
            "Updated Description",
            LocalDateTime.now(),
            LocalDateTime.now().plusHours(2),
            "Updated Location"
        );

        when(eventRepository.findById(any(EventId.class))).thenReturn(Optional.empty());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> useCase.execute(eventId, request));
        verify(eventRepository).findById(any(EventId.class));
        verify(eventRepository, never()).save(any(Event.class));
        verify(eventPublisher, never()).publish(any(EventUpdated.class));
    }
} 