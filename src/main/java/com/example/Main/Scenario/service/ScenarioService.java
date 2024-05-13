package com.example.Main.Scenario.service;

import com.example.Main.Scenario.Dto.DateRequestBody;
import com.example.Main.Scenario.Dto.EnableScenarioRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ScenarioService {
    ResponseEntity<JsonNode> getScenario(String auth);

    ResponseEntity<JsonNode> getScenarioById(String auth, String scenarioId);
    ResponseEntity<JsonNode> getScenarioByUserJourney(String auth, String scriptId);
    ResponseEntity<JsonNode> start(String auth, String scenarioId);
    ResponseEntity<JsonNode> stop(String auth, String scenarioId);
    ResponseEntity<JsonNode> enable(String auth, EnableScenarioRequest enableScenarioRequest);
    ResponseEntity<JsonNode> disable(String auth, EnableScenarioRequest enableScenarioRequest);
    ResponseEntity<JsonNode> getScenarioByWorkspace(String auth, String workspaceId);
    ResponseEntity<JsonNode> getSla(String auth, String scenarioId);
    ResponseEntity<JsonNode> getAvailability(String auth, String scenarioId, DateRequestBody dateRequest);
    ResponseEntity<JsonNode> getStatus(String auth);
    ResponseEntity<JsonNode> getResultsPerSites(String auth, String scenarioId);


    ResponseEntity<JsonNode> getRate(String auth, String scenarioId, DateRequestBody dateRequest);
    ResponseEntity<JsonNode> getSites(String auth, String scenarioId, DateRequestBody dateRequest);
    ResponseEntity<JsonNode> getRobots(String auth, String scenarioId, DateRequestBody dateRequest);












}