package com.example.Main.Zone.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface ZoneService {
    ResponseEntity<JsonNode> getZone(String auth);
    ResponseEntity<JsonNode> getZoneById(String auth , String zoneId);
}
