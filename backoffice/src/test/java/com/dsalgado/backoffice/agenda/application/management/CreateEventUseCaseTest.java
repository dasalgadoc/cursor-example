package com.dsalgado.backoffice.agenda.application.management;

import com.dsalgado.backoffice.agenda.application.dto.EventRequest;
import com.dsalgado.backoffice.agenda.application.dto.EventResponse;
import com.dsalgado.backoffice.agenda.domain.event.EventCreated;
import com.dsalgado.backoffice.agenda.domain.model.Event;
import com.dsalgado.backoffice.agenda.domain.port.EventPublisher;
import com.dsalgado.backoffice.agenda.domain.port.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateEventUseCaseTest {
    @Mock
    private EventRepository eventRepository;
    @Mock
    private EventPublisher eventPublisher;
    @Captor
    private ArgumentCaptor<EventCreated> eventCaptor;

    private CreateEventUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new CreateEventUseCase(eventRepository, eventPublisher);
    }

    @Test
    void shouldCreateEvent() {
        // Given
        EventRequest request = new EventRequest(
            "Test Event",
            "Test Description",
            LocalDateTime.now(),
            LocalDateTime.now().plusHours(2),
            "Test Location"
        );

        when(eventRepository.save(any(Event.class))).thenAnswer(i -> i.getArgument(0));

        // When
        EventResponse response = useCase.execute(request);

        // Then
        assertNotNull(response);
        assertEquals(request.name(), response.name());
        assertEquals(request.description(), response.description());
        assertEquals(request.startDate(), response.startDate());
        assertEquals(request.endDate(), response.endDate());
        assertEquals(request.location(), response.location());
        assertFalse(response.cancelled());

        verify(eventRepository).save(any(Event.class));
        verify(eventPublisher).publish(eventCaptor.capture());

        EventCreated publishedEvent = eventCaptor.getValue();
        assertEquals(request.name(), publishedEvent.name());
        assertEquals(request.startDate(), publishedEvent.startDate());
        assertEquals(request.endDate(), publishedEvent.endDate());
    }
} 