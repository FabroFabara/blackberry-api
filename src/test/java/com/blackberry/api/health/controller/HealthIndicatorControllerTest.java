package com.blackberry.api.health.controller;

import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.blackberry.api.health.service.HealthIndicatorService;


@ExtendWith(MockitoExtension.class)
public class HealthIndicatorControllerTest {
	
    @Mock
    private HealthIndicatorService healthIndicatorService;

    @InjectMocks
    private HealthIndicatorController healthIndicatorController;

    @Test
    public void getServiceHealth_ReturnsOk_WhenServiceIsHealthy() {
        when(healthIndicatorService.health()).thenReturn(Health.up().build());

        ResponseEntity<Map<String, Object>> response = healthIndicatorController.getServiceHealth();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getServiceHealth_ReturnsInternalServerError_WhenServiceIsDown() {
        when(healthIndicatorService.health()).thenReturn(Health.down().build());

        ResponseEntity<Map<String, Object>> response = healthIndicatorController.getServiceHealth();

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}