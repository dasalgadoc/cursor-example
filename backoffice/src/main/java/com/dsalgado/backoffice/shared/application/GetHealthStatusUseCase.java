package com.dsalgado.backoffice.shared.application;

import com.dsalgado.backoffice.shared.domain.HealthStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GetHealthStatusUseCase {
    private final String version;

    public GetHealthStatusUseCase(@Value("${app.version:1.0.0}") String version) {
        this.version = version;
    }

    public HealthStatus execute() {
        return HealthStatus.up(version);
    }
} 