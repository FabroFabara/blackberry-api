package com.blackberry.api.health.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.actuate.health.Health;
import static org.springframework.boot.actuate.health.Status.DOWN;
import static org.springframework.boot.actuate.health.Status.UP;

import java.util.Arrays;

import com.blackberry.api.actuator.ServiceHealthIndicator;

@ExtendWith(MockitoExtension.class)
public class HealthIndicatorServiceTest {

    @Mock
    private ServiceHealthIndicator healthIndicator;

    private HealthIndicatorService healthIndicatorService;


    @Test
    void whenAllLivenessIndicatorsAreHealthy_thenHealthStatusIsUp() {
        healthIndicatorService = new HealthIndicatorService(Arrays.asList(healthIndicator));
        when(healthIndicator.isHealth()).thenReturn(true);
        Health health = healthIndicatorService.health();
        assertEquals(UP, health.getStatus());
    }

    @Test
    void whenAllLivenessIndicatorsAreHealthy_thenHealthStatusIsDown() {
        healthIndicatorService = new HealthIndicatorService(Arrays.asList(healthIndicator));
        when(healthIndicator.isHealth()).thenReturn(false);
        Health health = healthIndicatorService.health();
        assertEquals(DOWN, health.getStatus());
    }
}
