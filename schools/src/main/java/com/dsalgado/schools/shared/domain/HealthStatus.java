package com.dsalgado.schools.shared.domain;

import lombok.Value;

@Value
public class HealthStatus {
    String status;
    String version;
    String timestamp;

    public static HealthStatus up(String version) {
        return new HealthStatus("UP", version, java.time.Instant.now().toString());
    }
} 