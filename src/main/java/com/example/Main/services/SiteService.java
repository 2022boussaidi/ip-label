package com.example.Main.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class SiteService {

    private final RestTemplate restTemplate;

    @Autowired
    public SiteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<JsonNode> getAllSites(String auth) {
        String apiUrl = "https://demo-ekara.ip-label.net/infra-api/sites";
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }

    public ResponseEntity<JsonNode> getQueueDetails(String auth, String siteId) {
        String apiUrl = "https://demo-ekara.ip-label.net/infra-api/site/" + siteId;
        HttpMethod method = HttpMethod.GET;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }
    public ResponseEntity<JsonNode> resetQueues(String auth, String siteId) {
        String apiUrl = "https://ekara-actif-collector-pub.ip-label.net/planner/site/" + siteId + "/resetQueues";
        HttpMethod method = HttpMethod.PATCH;
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
