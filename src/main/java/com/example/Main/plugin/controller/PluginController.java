package com.example.Main.plugin.controller;

import com.example.Main.Robot.service.RobotService;
import com.example.Main.plugin.service.PluginService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class PluginController {
    private final PluginService pluginService;
    private static final Logger LOG = Logger.getLogger(PluginController.class.getName());
    @Autowired
    public  PluginController(PluginService pluginService) {
        this.pluginService = pluginService;
    }



    @PostMapping("/plugins")
    public ResponseEntity<JsonNode> getPlugins(@RequestHeader("Authorization") String auth) {
        LOG.log(Level.INFO, "get plugins  is calling");
        return pluginService.getPlugins(auth);
    }
/******check this endpoint /consumption*****/
    @GetMapping("/consumption")
    public ResponseEntity<JsonNode> getConsumption(@RequestHeader("Authorization") String auth) {
        LOG.log(Level.INFO, "get consumption is calling");
        return pluginService.getConsumption(auth);
    }
}
