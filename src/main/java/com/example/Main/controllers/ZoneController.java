package com.example.Main.controllers;

import com.example.Main.services.ScenarioService;
import com.example.Main.services.ZoneService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZoneController {
    private  final ZoneService zoneService;

    @Autowired
    public   ZoneController (ZoneService zoneService){
        this.zoneService = zoneService ;
    }
    @PostMapping("/zones")
    public ResponseEntity<JsonNode> getZone(@RequestHeader("Authorization")String auth) {
        return zoneService.getZone(auth);
    }
}
