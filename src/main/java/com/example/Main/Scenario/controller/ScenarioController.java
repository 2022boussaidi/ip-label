package com.example.Main.Scenario.controller;

import com.example.Main.Scenario.Dto.DateRequestBody;
import com.example.Main.Scenario.Dto.EnableScenarioRequest;
import com.example.Main.Scenario.service.ScenarioService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ScenarioController {
    private final ScenarioService scenarioService;
    private static final Logger LOG = Logger.getLogger(ScenarioController.class.getName());


    @Autowired
    public ScenarioController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @PostMapping("/scenarios")
    public ResponseEntity<JsonNode> getScenario(@RequestHeader("Authorization") String auth) {
        LOG.log(Level.INFO, "get scenarios is calling");
        return scenarioService.getScenario(auth);
    }

    @GetMapping("/scenarios/{scenarioId}")
    public ResponseEntity<JsonNode> getScenarioById(@RequestHeader("Authorization") String auth, @PathVariable String scenarioId) {
        LOG.log(Level.INFO, "get scenario bu id  calling");
        return scenarioService.getScenarioById(auth, scenarioId);
    }

    @GetMapping("/scenario/{scenarioId}/start")
    public ResponseEntity<JsonNode> start(@RequestHeader("Authorization") String auth, @PathVariable String scenarioId) {
        LOG.log(Level.INFO, "start scenario by id  is calling");
        return scenarioService.start(auth, scenarioId);
    }

    @GetMapping("/scenario/{scenarioId}/stop")
    public ResponseEntity<JsonNode> stop(@RequestHeader("Authorization") String auth, @PathVariable String scenarioId) {
        LOG.log(Level.INFO, "stop scenario by id is calling");
        return scenarioService.stop(auth, scenarioId);
    }

    @PostMapping("/scenario/enable")
    public ResponseEntity<JsonNode> enable(@RequestHeader("Authorization") String auth, @RequestBody EnableScenarioRequest enableScenarioRequest) {
        LOG.log(Level.INFO, "enablescenario by id is calling");

        return scenarioService.enable(auth,enableScenarioRequest);
    }

    @PostMapping("/scenario/disable")
    public ResponseEntity<JsonNode> disable(@RequestHeader("Authorization") String auth,@RequestBody EnableScenarioRequest enableScenarioRequest) {
        LOG.log(Level.INFO, "disable scenario by id is calling");

        return scenarioService.disable(auth, enableScenarioRequest);
    }

    @PostMapping("/scenario/{workspaceId}")
    public ResponseEntity<JsonNode> getScenarioByWorkspaceId(@RequestHeader("Authorization") String auth, @PathVariable String workspaceId) {
        LOG.log(Level.INFO, "get scenario by workspace id is calling");

        return scenarioService.getScenarioByWorkspace(auth, workspaceId);
    }


    @PostMapping("/scenariosla/{scenarioId}")
    public ResponseEntity<JsonNode> getSla(@RequestHeader("Authorization") String auth, @PathVariable String scenarioId) {
        LOG.log(Level.INFO, "get scenario sla by id is calling");

        return scenarioService.getSla(auth, scenarioId);
    }

    @PostMapping("/scenario/availability/{scenarioId}")
    public ResponseEntity<JsonNode> getAvailability(

            @RequestHeader("Authorization") String auth,
            @PathVariable String scenarioId,
            @RequestBody DateRequestBody dateRequest) {
        LOG.log(Level.INFO, "get  scenario  availability by id is calling");

        // Call service method with the converted DateRequest
        return scenarioService.getAvailability(auth, scenarioId, dateRequest);
    }

    @PostMapping("/scenario/rate/{scenarioId}")
    public ResponseEntity<JsonNode> getRate(
            @RequestHeader("Authorization") String auth,
            @PathVariable String scenarioId,
            @RequestBody DateRequestBody dateRequest) {
        LOG.log(Level.INFO, "get  scenario rate  by id is calling");

        return scenarioService.getRate(auth, scenarioId, dateRequest);
    }
    @PostMapping("/scenario/site/{scenarioId}")
    public ResponseEntity<JsonNode> getRSites(
            @RequestHeader("Authorization") String auth,
            @PathVariable String scenarioId,
            @RequestBody DateRequestBody dateRequest) {
        LOG.log(Level.INFO, "get  sites list  by scenario id  is calling");

        return scenarioService.getSites(auth, scenarioId, dateRequest);
    }
    @PostMapping("/scenario/name_site/{scenarioId}")
    public ResponseEntity<JsonNode> getSitesName(
            @RequestHeader("Authorization") String auth,

            @PathVariable String scenarioId,
            @RequestBody DateRequestBody dateRequest) {
        LOG.log(Level.INFO, "get  sites name  by scenario id  is calling");

        return scenarioService.getSitesName(auth, scenarioId, dateRequest);
    }
    @PostMapping("/scenario/robot/{scenarioId}")
    public ResponseEntity<JsonNode> getRobots(
            @RequestHeader("Authorization") String auth,
            @PathVariable String scenarioId,
            @RequestBody DateRequestBody dateRequest) {
        LOG.log(Level.INFO, "get  robots list  by scenario id  is calling");

        return scenarioService.getRobots(auth, scenarioId, dateRequest);
    }



    @PostMapping("/scenario/status")
    public ResponseEntity<JsonNode> getStatus(@RequestHeader("Authorization") String auth) {
        LOG.log(Level.INFO, "get  scenario status  by scenario id  is calling");

        return scenarioService.getStatus(auth);
    }

    @PostMapping("/scenario/resultspersites/{scenarioId}")
    public ResponseEntity<JsonNode> getResultsPerSites(@RequestHeader("Authorization") String auth ,@PathVariable String scenarioId) {
        return scenarioService.getResultsPerSites(auth,scenarioId)   ; }


}