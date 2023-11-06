package com.blackberry.api.health.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.actuate.health.Health;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blackberry.api.actuator.ServiceHealthIndicator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class HealthIndicatorService {

    private final List<ServiceHealthIndicator> healthIndicatorList = new ArrayList<>();

    public Health health() {
        boolean isAllLive = healthIndicatorList.stream().allMatch(ServiceHealthIndicator::isHealth);
        if (isAllLive) {
            return buildHealthyHealth();
        } else {
            return buildUnHealthyHealth();
        }
    }

    private Health buildHealthyHealth() {
        HttpStatus status = HttpStatus.OK;
        Map<String, Object> healthResponse = buildMessageResponse(buildHealthyResponseMessage(), status);
        return Health.up().withDetails(healthResponse).build();
    }

    private Health buildUnHealthyHealth() {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Map<String, Object> healthResponse = buildMessageResponse(buildUnhealthyResponseMessage(), status);
        return Health.down().withDetails(healthResponse).build();
    }

    private Map<String, Object> buildMessageResponse(String message, HttpStatus status) {
        Map<String, Object> healthResponse = new HashMap<>();
        healthResponse.put("message", message);
        healthResponse.put("code", status.value());
        healthResponse.put("status", status.name());
        return healthResponse;
    }

    private String buildHealthyResponseMessage() {
        return String.format("Healthy response from service at %s", LocalDateTime.now());
    }

    private String buildUnhealthyResponseMessage() {
        return String.format("Unhealthy response from service at %s", LocalDateTime.now());
    }

}