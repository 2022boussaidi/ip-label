package com.example.Main.Inventory.controller;

import com.example.Main.Inventory.service.InventoryService;
import com.example.Main.Inventory.service.InventoryServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {
    private  final InventoryService inventoryService;
    @Autowired
    public InventoryController(InventoryServiceImpl inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/inventory")
    public ResponseEntity<JsonNode> getInventory(@RequestHeader("Authorization") String auth) {
        return inventoryService.getInventory(auth);
    }

}
