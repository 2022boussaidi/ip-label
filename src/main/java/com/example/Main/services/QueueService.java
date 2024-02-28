package com.example.Main.services;

import com.example.Main.managers.SessionManager;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class QueueService {

    private final RestTemplate restTemplate;

    @Autowired
    public QueueService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<JsonNode> getAllQueues(String auth) {
        String apiUrl = "https://demo-ekara.ip-label.net/infra-api/queues";
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }

    public ResponseEntity<JsonNode> startQueue(String auth, String queueId) {
        String apiUrl ="https://demo-ekara.ip-label.net/infra-api/queue/" + queueId + "/start";
        HttpMethod method = HttpMethod.GET;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }
    public ResponseEntity<JsonNode> stopQueue(String auth, String queueId) {
        String apiUrl ="https://demo-ekara.ip-label.net/infra-api/queue/" + queueId + "/stop";
        HttpMethod method = HttpMethod.PATCH;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }
    public ResponseEntity<JsonNode> resetQueue(String auth, String queueId) {
        String apiUrl ="https://ekara-actif-collector-pub.ip-label.net/planner/queue/" + queueId + "/reset";
        HttpMethod method = HttpMethod.PATCH;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }
    public ResponseEntity<JsonNode> reserveQueue(String auth, String queueId) {
        String apiUrl ="https://demo-ekara.ip-label.net/infra-api/queue/" + queueId + "/reserve";
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
