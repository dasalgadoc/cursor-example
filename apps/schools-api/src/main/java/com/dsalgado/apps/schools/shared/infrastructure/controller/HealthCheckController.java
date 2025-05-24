package com.dsalgado.apps.schools.shared.infrastructure.controller;

import com.dsalgado.apps.schools.shared.application.GetHealthStatusUseCase;
import com.dsalgado.apps.schools.shared.domain.HealthStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
@Tag(name = "Health Check", description = "Health check endpoints")
public class HealthCheckController {
    private final GetHealthStatusUseCase getHealthStatusUseCase;

    public HealthCheckController(GetHealthStatusUseCase getHealthStatusUseCase) {
        this.getHealthStatusUseCase = getHealthStatusUseCase;
    }

    @GetMapping
    @Operation(summary = "Get API health status")
    public ResponseEntity<HealthStatus> getHealthStatus() {
        return ResponseEntity.ok(getHealthStatusUseCase.execute());
    }
} 