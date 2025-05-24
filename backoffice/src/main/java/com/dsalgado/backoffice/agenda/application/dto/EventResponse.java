package com.dsalgado.backoffice.agenda.application.dto;

import java.time.LocalDateTime;

public record EventResponse(
    String id,
    String name,
    String description,
    LocalDateTime startDate,
    LocalDateTime endDate,
    String location,
    boolean cancelled,
    LocalDateTime lastModified
) {} 