package com.blackberry.api.health.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Greetings API", description = "This API returna Hello World")
public class HelloController {
	
	@GetMapping("/greetings")
	@Operation(summary = "Get Greetings", description = "Get a greeting message.")
	public ResponseEntity<String> greetings(){
		return ResponseEntity.ok("Hello, World!");
	}
}
