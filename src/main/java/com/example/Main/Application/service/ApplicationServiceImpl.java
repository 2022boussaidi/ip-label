package com.example.Main.Application.service;



import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
@Service
public class ApplicationServiceImpl implements ApplicationService  {
    private final RestTemplate restTemplate;

    @Autowired
    public ApplicationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<JsonNode> getApplication(String auth) {
        String apiUrl = "https://api.ekara.ip-label.net/adm-api/applications";
        HttpMethod method = HttpMethod.GET;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }
    public ResponseEntity<JsonNode> getApplicationById(String auth, String applicationId) {

        ResponseEntity<JsonNode> allApplicationsResponse = getApplication(auth);

        if (allApplicationsResponse.getStatusCode().is2xxSuccessful()) {
            // Parse JSON response
            JsonNode applicationsNode = allApplicationsResponse.getBody();


            for (JsonNode applicationNode : applicationsNode) {
                String id = applicationNode.get("id").asText();
                if (id.equals(applicationId)) {
                    // If app with the matching ID is found, return it
                    return ResponseEntity.ok(applicationNode);
                }
            }

            // If no app with the provided ID is found, return 404 Not Found
            return ResponseEntity.notFound().build();
        } else {
            // If there is an error fetching all apps, return the error response
            return allApplicationsResponse;
        }
    }
    public ResponseEntity<JsonNode> getApplicationByWorkspace(String auth, String workspaceId) {
        String apiUrl = "https://api.ekara.ip-label.net/adm-api/applications" + workspaceId;
        HttpMethod method = HttpMethod.GET;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
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
