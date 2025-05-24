package com.dsalgado.backoffice.agenda.domain.port;

import com.dsalgado.backoffice.agenda.domain.event.EventCreated;
import com.dsalgado.backoffice.agenda.domain.event.EventUpdated;
import com.dsalgado.backoffice.agenda.domain.event.EventDeleted;

public interface EventPublisher {
    void publish(EventCreated event);
    void publish(EventUpdated event);
    void publish(EventDeleted event);
} 