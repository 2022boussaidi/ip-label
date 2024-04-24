package com.example.Main.Inventory.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface InventoryService {
    ResponseEntity<JsonNode> getInventory(String auth);
}
