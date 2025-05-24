package com.dsalgado.backoffice.agenda.infrastructure.messaging;

import com.dsalgado.backoffice.agenda.domain.event.EventCreated;
import com.dsalgado.backoffice.agenda.domain.port.EventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemoryEventPublisher implements EventPublisher {
    private static final Logger logger = LoggerFactory.getLogger(InMemoryEventPublisher.class);

    @Override
    public void publish(EventCreated event) {
        logger.info("Event created: {}", event);
        // In a real implementation, this would publish to a message broker
    }
} 