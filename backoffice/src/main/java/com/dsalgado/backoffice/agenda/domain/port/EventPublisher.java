package com.dsalgado.backoffice.agenda.domain.port;

import com.dsalgado.backoffice.agenda.domain.event.EventCreated;

public interface EventPublisher {
    void publish(EventCreated event);
} 