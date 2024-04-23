package com.example.Main.Scenario.controller;

import com.example.Main.Scenario.service.ScenarioService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScenarioController {
    private  final ScenarioService scenarioService;

    @Autowired
    public   ScenarioController (ScenarioService scenarioService){
        this.scenarioService = scenarioService ;
    }
    @PostMapping("/scenarios")
    public ResponseEntity<JsonNode> getScenario(@RequestHeader("Authorization")String auth) {
        return scenarioService.getScenario(auth);
    }
    @GetMapping("/scenarios/{scenarioId}")
    public ResponseEntity<JsonNode> getScenarioById(@RequestHeader("Authorization") String auth,@PathVariable String scenarioId) {
        return scenarioService.getScenarioById(auth,scenarioId);
    }
}
