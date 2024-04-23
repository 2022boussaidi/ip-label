package com.example.Main.Auth.controller;

import com.example.Main.managers.SessionManager;
import com.example.Main.Auth.model.LoginRequest;
import com.example.Main.Auth.service.TokenService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private TokenService tokenService; // Inject the TokenService

    @PostMapping("/login")
    public ResponseEntity<JsonNode> login(@RequestBody LoginRequest loginRequest) {
        // Define the API endpoint URL
        String apiUrl = "https://api.ekara.ip-label.net/auth/login";

        // Set the request headers (content type)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create a HttpEntity with the request body and headers
        HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(loginRequest, headers);

        // Make the POST request to the API endpoint
        try {
            // Make the request with headers
            ResponseEntity<JsonNode> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, JsonNode.class);
            String accessToken = tokenService.extractAccessToken(response.getBody().toString()); // Use TokenService
            sessionManager.setAccessToken(accessToken); // Store access token in session manager
            return ResponseEntity.ok(response.getBody());

        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getRawStatusCode()).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
