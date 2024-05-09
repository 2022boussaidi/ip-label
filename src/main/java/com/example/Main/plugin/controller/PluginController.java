package com.example.Main.plugin.controller;

import com.example.Main.Robot.service.RobotService;
import com.example.Main.plugin.service.PluginService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PluginController {
    private final PluginService pluginService;

    @Autowired
    public  PluginController(PluginService pluginService) {
        this.pluginService = pluginService;
    }



    @PostMapping("/plugins")
    public ResponseEntity<JsonNode> getPlugins(@RequestHeader("Authorization") String auth) {
        return pluginService.getPlugins(auth);
    }
/******check this endpoint /consumption*****/
    @GetMapping("/consumption")
    public ResponseEntity<JsonNode> getConsumption(@RequestHeader("Authorization") String auth) {
        return pluginService.getConsumption(auth);
    }
}
