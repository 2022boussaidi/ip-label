package com.example.Main.Scenario.controller;

import com.example.Main.Scenario.model.DateRequest;
import com.example.Main.Scenario.service.ScenarioService;
import com.example.Main.Scenario.service.ScenarioServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScenarioController {
    private final ScenarioService scenarioService;

    @Autowired
    public ScenarioController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @PostMapping("/scenarios")
    public ResponseEntity<JsonNode> getScenario(@RequestHeader("Authorization") String auth) {
        return scenarioService.getScenario(auth);
    }

    @GetMapping("/scenarios/{scenarioId}")
    public ResponseEntity<JsonNode> getScenarioById(@RequestHeader("Authorization") String auth, @PathVariable String scenarioId) {
        return scenarioService.getScenarioById(auth, scenarioId);
    }

    @GetMapping("/scenario/{scenarioId}/start")
    public ResponseEntity<JsonNode> start(@RequestHeader("Authorization") String auth, @PathVariable String scenarioId) {
        return scenarioService.start(auth, scenarioId);
    }

    @GetMapping("/scenario/{scenarioId}/stop")
    public ResponseEntity<JsonNode> stop(@RequestHeader("Authorization") String auth, @PathVariable String scenarioId) {
        return scenarioService.stop(auth, scenarioId);
    }

    @PostMapping("/scenario/{scenarioId}/enable")
    public ResponseEntity<JsonNode> enable(@RequestHeader("Authorization") String auth, @PathVariable String scenarioId) {
        return scenarioService.enable(auth, scenarioId);
    }

    @PostMapping("/scenario/{scenarioId}/disable")
    public ResponseEntity<JsonNode> disable(@RequestHeader("Authorization") String auth, @PathVariable String scenarioId) {
        return scenarioService.disable(auth, scenarioId);
    }

    @PostMapping("/scenario/{workspaceId}")
    public ResponseEntity<JsonNode> getScenarioByWorkspaceId(@RequestHeader("Authorization") String auth, @PathVariable String workspaceId) {
        return scenarioService.getScenarioByWorkspace(auth, workspaceId);
    }


    @PostMapping("/scenariosla/{scenarioId}")
    public ResponseEntity<JsonNode> getSla(@RequestHeader("Authorization") String auth, @PathVariable String scenarioId) {
        return scenarioService.getSla(auth, scenarioId);
    }

    @PostMapping("/scenario/availability/{scenarioId}")
    public ResponseEntity<JsonNode> getAvailibility(@RequestHeader("Authorization") String auth, @PathVariable String scenarioId, @RequestBody DateRequest dateRequest) {
        return scenarioService.getAvailability(auth, scenarioId, dateRequest);
    }

    @PostMapping("/scenario/status")
    public ResponseEntity<JsonNode> getStatus(@RequestHeader("Authorization") String auth) {
        return scenarioService.getStatus(auth);
    }

    @PostMapping("/scenario/resultspersites/{scenarioId}")
    public ResponseEntity<JsonNode> getResultsPerSites(@RequestHeader("Authorization") String auth ,@PathVariable String scenarioId) {
        return scenarioService.getResultsPerSites(auth,scenarioId)   ; }


}