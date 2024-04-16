package com.example.Main.controllers;

import com.example.Main.services.ClientService;
import com.example.Main.services.ScenarioService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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
}
