package com.example.Main.Scenario.service;

import com.example.Main.Scenario.Dto.DateRequestBody;
import com.example.Main.Scenario.Dto.EnableScenarioRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class ScenarioServiceImpl implements ScenarioService  {
    private final RestTemplate restTemplate;

    @Autowired
    public ScenarioServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<JsonNode> getScenario(String auth) {
        String apiUrl = "https://api.ekara.ip-label.net/adm-api/scenarios";
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
    public ResponseEntity<JsonNode> getScenarioByUserJourney(String auth , String scriptId) {
        String apiUrl = "https://api.ekara.ip-label.net/adm-api/scenario/script/"+scriptId;
        HttpMethod method = HttpMethod.GET;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }
    public ResponseEntity<JsonNode> start(String auth, String scenarioId) {
        String apiUrl = "https://api.ekara.ip-label.net/adm-api/scenario/"+scenarioId+"/start";
        HttpMethod method = HttpMethod.GET;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }
    public ResponseEntity<JsonNode> stop(String auth, String scenarioId) {
        String apiUrl = "https://api.ekara.ip-label.net/adm-api/scenario/" + scenarioId + "/stop";
        HttpMethod method = HttpMethod.GET;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }
    public ResponseEntity<JsonNode> enable(String auth ,EnableScenarioRequest  enableScenarioRequest) {
        String apiUrl = "https://api.ekara.ip-label.net/adm-api/scenarios/start";
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }
    public ResponseEntity<JsonNode> disable(String auth, EnableScenarioRequest enableScenarioRequest) {
        String apiUrl = "https://api.ekara.ip-label.net/adm-api/scenarios/stop";
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }
    public ResponseEntity<JsonNode> getScenarioByWorkspace(String auth, String workspaceId) {
        String apiUrl = "https://api.ekara.ip-label.net/adm-api/scenarios/"+workspaceId;
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }
    public ResponseEntity<JsonNode> getSla(String auth, String scenarioId) {
        String apiUrl = "https://api.ekara.ip-label.net/adm-api/scenariosla/"+ scenarioId;
        HttpMethod method = HttpMethod.GET;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }

    public ResponseEntity<JsonNode> getAvailability(String auth, String scenarioId, DateRequestBody dateRequest) {
        String apiUrl = "https://api.ekara.ip-label.net/results-api/availability/" + scenarioId;
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);

        // Pass dateRequest as request body
        ResponseEntity<JsonNode> response = restTemplate.exchange(apiUrl, method, new HttpEntity<>(dateRequest, headers), JsonNode.class);

        return response;
    }
    public ResponseEntity<JsonNode> getStatus(String auth) {
        String apiUrl = "https://api.ekara.ip-label.net/results-api/scenarios/status";
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }

    public ResponseEntity<JsonNode> getResultsPerSites(String auth , String scenarioId) {
        String apiUrl = "https://api.ekara.ip-label.net/results-api/resultspersites/"+scenarioId;
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }



    public ResponseEntity<JsonNode> getRate(String auth, String scenarioId , DateRequestBody dateRequest) {
        String apiUrl = "https://api.ekara.ip-label.net/results-api/availability/" + scenarioId;
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);

        // Pass dateRequest as request body
        ResponseEntity<JsonNode> response = restTemplate.exchange(apiUrl, method, new HttpEntity<>(dateRequest, headers), JsonNode.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            // Parse JSON response
            JsonNode jsonResponse = response.getBody();

            // Extract "ratePercent" field
            Double ratePercent = jsonResponse.get("ratePercent").asDouble();

            // Prepare JSON response object
            ObjectMapper mapper = new ObjectMapper();
            Object responseObject;

            // Check if ratePercent is >= 90
            if (ratePercent >= 90) {
                responseObject = mapper.createObjectNode().put("message", "Scenario is performing well");
            } else {
                responseObject = mapper.createObjectNode().put("message", "Scenario is not performing well").put("ratePercent", ratePercent);
            }

            // Return response
            return ResponseEntity.ok((JsonNode) responseObject);
        } else {
            // If there is an error, return the error response
            return ResponseEntity.status(response.getStatusCode()).build();
        }
    }
    public ResponseEntity<JsonNode> getRobots(String auth, String scenarioId, DateRequestBody dateRequest) {
        String apiUrl = "https://api.ekara.ip-label.net/results-api/availability/" + scenarioId;
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);

        // Pass dateRequest as request body
        ResponseEntity<JsonNode> response = restTemplate.exchange(apiUrl, method, new HttpEntity<>(dateRequest, headers), JsonNode.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            // Parse JSON response
            JsonNode jsonResponse = response.getBody();

            // Extract sites IDs
            Set<String> siteIds = new HashSet<>();
            JsonNode details = jsonResponse.get("details");
            if (details != null && details.isArray()) {
                for (JsonNode detail : details) {
                    JsonNode execs = detail.get("execs");
                    if (execs != null && execs.isArray()) {
                        for (JsonNode exec : execs) {
                            String siteId = exec.get("siteId").asText();
                            siteIds.add(siteId);
                        }
                    }
                }
            }

            // Prepare JSON response object
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode responseObject = mapper.createObjectNode();
            ArrayNode siteIdsArray = mapper.createArrayNode();
            siteIds.forEach(siteIdsArray::add);
            responseObject.set("siteIds", siteIdsArray);

            // Return response
            return ResponseEntity.ok(responseObject);
        } else {
            // If there is an error, return the error response
            return ResponseEntity.status(response.getStatusCode()).build();
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
