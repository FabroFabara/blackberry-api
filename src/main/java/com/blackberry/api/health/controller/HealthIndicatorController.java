package com.blackberry.api.health.controller;

import static org.springframework.boot.actuate.health.Status.UP;

import java.util.Map;

import org.springframework.boot.actuate.health.Health;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackberry.api.health.service.HealthIndicatorService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/health")
@Tag(name = "Health API Status", description = "API related to the health endpoint status")
public class HealthIndicatorController {
	
    private final HealthIndicatorService healthCheckService;
   

    @GetMapping(path = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Save a new client", responses = {
            @ApiResponse(responseCode = "200", description = "Health is UP"),
            @ApiResponse(responseCode = "400", description = "Health is DOWN"),
            @ApiResponse(responseCode = "500", description = "Health is DOWN")
    })
    public ResponseEntity<Map<String, Object>> getServiceHealth() {
        Health healthResponse = healthCheckService.health();
        HttpStatus httpStatus = UP.equals(healthResponse.getStatus()) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(httpStatus.value()).body(healthResponse.getDetails());
    }

}