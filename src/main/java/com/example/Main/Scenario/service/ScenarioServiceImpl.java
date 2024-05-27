package com.example.Main.Scenario.service;

import com.example.Main.Inventory.service.InventoryService;
import com.example.Main.Scenario.Dto.DateRequestBody;
import com.example.Main.Scenario.Dto.EnableScenarioRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ScenarioServiceImpl implements ScenarioService  {
    private final RestTemplate restTemplate;
    private final InventoryService inventoryService;

    private static final Logger LOG = Logger.getLogger(ScenarioServiceImpl.class.getName());


    @Autowired
    public ScenarioServiceImpl(RestTemplate restTemplate, InventoryService inventoryService) {
        this.restTemplate = restTemplate;
        this.inventoryService = inventoryService;
    }

    public ResponseEntity<JsonNode> getScenario(String auth) {
        LOG.log(Level.INFO, "get scenarios is calling");
        String apiUrl = "https://api.ekara.ip-label.net/adm-api/scenarios";
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }
    public ResponseEntity<JsonNode> getScenarioById(String auth, String scenarioId) {
        LOG.log(Level.INFO, "get scenario by id is calling");

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
        LOG.log(Level.INFO, "start scenarion by id is calling");

        String apiUrl = "https://api.ekara.ip-label.net/adm-api/scenario/"+scenarioId+"/start";
        HttpMethod method = HttpMethod.GET;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }
    public ResponseEntity<JsonNode> stop(String auth, String scenarioId) {
        LOG.log(Level.INFO, "stop scenario bu id  is calling");

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
        LOG.log(Level.INFO, "get scenario  availability by id is calling");

        String apiUrl = "https://api.ekara.ip-label.net/results-api/availability/" + scenarioId;
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);

        // Pass dateRequest as request body
        ResponseEntity<JsonNode> response = restTemplate.exchange(apiUrl, method, new HttpEntity<>(dateRequest, headers), JsonNode.class);

        return response;
    }
    public ResponseEntity<JsonNode> getStatus(String auth) {
        LOG.log(Level.INFO, "get scenarios status  is calling");

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
        LOG.log(Level.INFO, "get scenario rate by id  is calling");

        String apiUrl = "https://api.ekara.ip-label.net/results-api/availability/" + scenarioId;
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);

        // Pass dateRequest as request body
        ResponseEntity<JsonNode> response = restTemplate.exchange(apiUrl, method, new HttpEntity<>(dateRequest, headers), JsonNode.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            // Parse JSON response

            // Extract sites IDs
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
    public ResponseEntity<JsonNode> getSitesName(String auth, String scenarioId, DateRequestBody dateRequest) {
        LOG.log(Level.INFO, "get sites name by scenario id  is calling");

        String apiUrl = "https://api.ekara.ip-label.net/results-api/availability/" + scenarioId;
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);

        // Pass dateRequest as request body
        ResponseEntity<JsonNode> response = restTemplate.exchange(apiUrl, method, new HttpEntity<>(dateRequest, headers), JsonNode.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            // Parse JSON response
            JsonNode jsonResponse = response.getBody();
            Set<String> siteIds = new HashSet<>();
            JsonNode details = jsonResponse.get("details");
            if (details != null && details.isArray()) {
                for (JsonNode detail : details) {
                    JsonNode execs = detail.get("execs");
                    if (execs != null && execs.isArray()) {
                        for (JsonNode exec : execs) {
                            String siteId = exec.get("siteName").asText();
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
            responseObject.set("siteNames", siteIdsArray);

            // Return response
            return ResponseEntity.ok(responseObject);
        } else {
            // If there is an error, return the error response
            return ResponseEntity.status(response.getStatusCode()).build();
        }
    }
    public ResponseEntity<JsonNode> getSites(String auth, String scenarioId, DateRequestBody dateRequest) {
        LOG.log(Level.INFO, "get sites list is calling");

        String apiUrl = "https://api.ekara.ip-label.net/results-api/availability/" + scenarioId;
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);

        // Pass dateRequest as request body
        ResponseEntity<JsonNode> response = restTemplate.exchange(apiUrl, method, new HttpEntity<>(dateRequest, headers), JsonNode.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            // Parse JSON response
            JsonNode jsonResponse = response.getBody();
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


    public ResponseEntity<JsonNode> getRobots(String auth, String scenarioId, DateRequestBody dateRequest) {
        LOG.log(Level.INFO, "get robots list  is calling");

        // Get the sites data
        ResponseEntity<JsonNode> sitesResponse = getSites(auth, scenarioId, dateRequest);
        if (sitesResponse.getStatusCode().is2xxSuccessful()) {
            JsonNode sitesData = sitesResponse.getBody();
            if (sitesData != null) {
                // Extract the list of site IDs from the sites data
                List<String> siteIds = extractSiteIds(sitesData);

                // Get the inventory data
                ResponseEntity<JsonNode> inventoryResponse = inventoryService.getInventory(auth);
                if (inventoryResponse.getStatusCode().is2xxSuccessful()) {
                    JsonNode inventoryData = inventoryResponse.getBody();
                    if (inventoryData != null) {
                        // Process inventory data for each site
                        ArrayNode processedInventory = processInventoryForSites(inventoryData, siteIds);

                        // Return the processed inventory data
                        return ResponseEntity.ok().body(processedInventory);
                    } else {
                        // Handle null inventory data
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                    }
                } else {
                    // Handle non-successful response from inventory service
                    return ResponseEntity.status(inventoryResponse.getStatusCode()).body(null);
                }
            } else {
                // Handle null sites data
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            // Handle non-successful response from sites service
            return ResponseEntity.status(sitesResponse.getStatusCode()).body(null);
        }
    }

    private List<String> extractSiteIds(JsonNode sitesData) {
        LOG.log(Level.INFO, "extract sites ids  is calling");

        List<String> siteIds = new ArrayList<>();
        JsonNode siteIdsArray = sitesData.get("siteIds");
        if (siteIdsArray != null && siteIdsArray.isArray()) {
            for (JsonNode siteIdNode : siteIdsArray) {
                String siteId = siteIdNode.asText();
                siteIds.add(siteId);
            }
        }
        return siteIds;
    }

    private ArrayNode processInventoryForSites(JsonNode inventoryData, List<String> siteIds) {
        LOG.log(Level.INFO, "processInventoryForSites is calling");

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode processedInventory = mapper.createArrayNode();
        JsonNode inventories = inventoryData.get("inventories");
        for (JsonNode inventory : inventories) {
            String inventorySiteId = inventory.get("site_id").asText();
            if (siteIds.contains(inventorySiteId)) {
                processedInventory.add(inventory);
            }
        }
        return processedInventory;
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
