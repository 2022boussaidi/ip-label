package com.example.Main.Zone.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
@Service
public class ZoneService {
    private final RestTemplate restTemplate;

    @Autowired
    public ZoneService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<JsonNode>getZone(String auth) {
        String apiUrl = "https://demo-ekara.ip-label.net/adm-api/zones";
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }
    public ResponseEntity<JsonNode> getZoneById(String auth, String zoneId) {

        ResponseEntity<JsonNode> allZonesResponse = getZone(auth);
        if (allZonesResponse.getStatusCode().is2xxSuccessful()) {
            JsonNode zonesNode = allZonesResponse.getBody();
            for (JsonNode zoneNode : zonesNode) {
                String id = zoneNode.get("id").asText();
                if (id.equals(zoneId)) {
                    return ResponseEntity.ok(zoneNode);
                }
            }
            return ResponseEntity.notFound().build();
        } else {
            // If there is an error fetching all zones, return the error response
            return allZonesResponse;
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
