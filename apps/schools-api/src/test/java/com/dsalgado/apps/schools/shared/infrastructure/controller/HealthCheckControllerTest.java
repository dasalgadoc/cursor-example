package com.dsalgado.apps.schools.shared.infrastructure.controller;

import com.dsalgado.apps.schools.shared.application.GetHealthStatusUseCase;
import com.dsalgado.apps.schools.shared.domain.HealthStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HealthCheckController.class)
class HealthCheckControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetHealthStatusUseCase getHealthStatusUseCase;

    @Test
    void shouldReturnHealthStatus() throws Exception {
        // Given
        HealthStatus healthStatus = HealthStatus.up("1.0.0");
        when(getHealthStatusUseCase.execute()).thenReturn(healthStatus);

        // When/Then
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.version").value("1.0.0"))
                .andExpect(jsonPath("$.timestamp").exists());
    }
} 