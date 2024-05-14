package com.example.Main.Scenario.controller;

import com.example.Main.Scenario.Dto.DateRequestBody;
import com.example.Main.Scenario.Dto.EnableScenarioRequest;
import com.example.Main.Scenario.service.ScenarioService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/scenario/enable")
    public ResponseEntity<JsonNode> enable(@RequestHeader("Authorization") String auth, @RequestBody EnableScenarioRequest enableScenarioRequest) {
        return scenarioService.enable(auth,enableScenarioRequest);
    }

    @PostMapping("/scenario/disable")
    public ResponseEntity<JsonNode> disable(@RequestHeader("Authorization") String auth,@RequestBody EnableScenarioRequest enableScenarioRequest) {
        return scenarioService.disable(auth, enableScenarioRequest);
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
    public ResponseEntity<JsonNode> getAvailability(
            @RequestHeader("Authorization") String auth,
            @PathVariable String scenarioId,
            @RequestBody DateRequestBody dateRequest) {
        // Call service method with the converted DateRequest
        return scenarioService.getAvailability(auth, scenarioId, dateRequest);
    }

    @PostMapping("/scenario/rate/{scenarioId}")
    public ResponseEntity<JsonNode> getRate(
            @RequestHeader("Authorization") String auth,
            @PathVariable String scenarioId,
            @RequestBody DateRequestBody dateRequest) {

        return scenarioService.getRate(auth, scenarioId, dateRequest);
    }
    @PostMapping("/scenario/site/{scenarioId}")
    public ResponseEntity<JsonNode> getRSites(
            @RequestHeader("Authorization") String auth,
            @PathVariable String scenarioId,
            @RequestBody DateRequestBody dateRequest) {

        return scenarioService.getSites(auth, scenarioId, dateRequest);
    }
    @PostMapping("/scenario/name_site/{scenarioId}")
    public ResponseEntity<JsonNode> getSitesName(
            @RequestHeader("Authorization") String auth,

            @PathVariable String scenarioId,
            @RequestBody DateRequestBody dateRequest) {

        return scenarioService.getSitesName(auth, scenarioId, dateRequest);
    }
    @PostMapping("/scenario/robot/{scenarioId}")
    public ResponseEntity<JsonNode> getRobots(
            @RequestHeader("Authorization") String auth,
            @PathVariable String scenarioId,
            @RequestBody DateRequestBody dateRequest) {

        return scenarioService.getRobots(auth, scenarioId, dateRequest);
    }



    @PostMapping("/scenario/status")
    public ResponseEntity<JsonNode> getStatus(@RequestHeader("Authorization") String auth) {
        return scenarioService.getStatus(auth);
    }

    @PostMapping("/scenario/resultspersites/{scenarioId}")
    public ResponseEntity<JsonNode> getResultsPerSites(@RequestHeader("Authorization") String auth ,@PathVariable String scenarioId) {
        return scenarioService.getResultsPerSites(auth,scenarioId)   ; }


}