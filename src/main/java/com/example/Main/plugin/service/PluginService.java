package com.example.Main.plugin.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface PluginService {
    ResponseEntity<JsonNode> getPlugins(String auth);
    ResponseEntity<JsonNode> getConsumption(String auth);
}
