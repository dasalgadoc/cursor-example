package com.dsalgado.schools.domain.events.application.usecase;

import com.dsalgado.schools.domain.events.domain.model.Event;
import com.dsalgado.schools.domain.events.domain.port.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateEventUseCaseTest {
    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private CreateEventUseCase createEventUseCase;

    @Test
    void shouldCreateEvent() {
        // Given
        String title = "Test Event";
        String description = "Test Description";
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusHours(2);
        String location = "Test Location";
        Event expectedEvent = new Event(title, description, startDate, endDate, location);

        when(eventRepository.save(any(Event.class))).thenReturn(expectedEvent);

        // When
        Event result = createEventUseCase.execute(title, description, startDate, endDate, location);

        // Then
        assertNotNull(result);
        assertEquals(title, result.getTitle());
        assertEquals(description, result.getDescription());
        assertEquals(startDate, result.getStartDate());
        assertEquals(endDate, result.getEndDate());
        assertEquals(location, result.getLocation());
        verify(eventRepository).save(any(Event.class));
    }
} 