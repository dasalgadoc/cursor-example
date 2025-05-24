package com.dsalgado.backoffice.agenda.application.dto;

import java.time.LocalDateTime;

public record EventRequest(
    String name,
    String description,
    LocalDateTime startDate,
    LocalDateTime endDate,
    String location
) {} 