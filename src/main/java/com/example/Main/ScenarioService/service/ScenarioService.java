package com.example.Main.ScenarioService.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
@Service
public class ScenarioService {
    private final RestTemplate restTemplate;

    @Autowired
    public ScenarioService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<JsonNode> getScenario(String auth) {
        String apiUrl = "https://demo-ekara.ip-label.net/adm-api/scenarios";
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }
    public ResponseEntity<JsonNode> getScenarioById(String auth, String scenarioId) {
        // Fetch all users
        ResponseEntity<JsonNode> allUsersResponse = getScenario(auth);

        if (allUsersResponse.getStatusCode().is2xxSuccessful()) {
            // Parse JSON response
            JsonNode scenariosNode = allUsersResponse.getBody();

            // Iterate through users to find the one with the matching ID
            for (JsonNode scenarioNode : scenariosNode) {
                String id = scenarioNode.get("id").asText();
                if (id.equals(scenarioId)) {
                    // If user with the matching ID is found, return it
                    return ResponseEntity.ok(scenarioNode);
                }
            }

            // If no user with the provided ID is found, return 404 Not Found
            return ResponseEntity.notFound().build();
        } else {
            // If there is an error fetching all users, return the error response
            return allUsersResponse;
        }
    }

    private String extractToken(String authorizationHeader) {
        String[] parts = authorizationHeader.split(" ");
        if (parts.length == 2 && parts[0].equalsIgnoreCase("Bearer")) {
            return parts[1];
        } else {
            throw new IllegalArgumentException("Invalid Authorization header format");
        }
    }

    private HttpHeaders createHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    private ResponseEntity<JsonNode> executeRequest(String apiUrl, HttpMethod method, HttpHeaders headers, Class<JsonNode> responseType) {
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        try {
            return restTemplate.exchange(apiUrl, method, requestEntity, responseType);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getRawStatusCode()).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
