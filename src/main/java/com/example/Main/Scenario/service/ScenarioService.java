package com.example.Main.Scenario.service;

import com.example.Main.Scenario.Dto.DateRequest;
import com.example.Main.Scenario.Dto.EnableScenarioRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

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
    ResponseEntity<JsonNode> getAvailability(String auth, String scenarioId, DateRequest dateRequest);
    ResponseEntity<JsonNode> getStatus(String auth);
    ResponseEntity<JsonNode> getResultsPerSites(String auth, String scenarioId);









}