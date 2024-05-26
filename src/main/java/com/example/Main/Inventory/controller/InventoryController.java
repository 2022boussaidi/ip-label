package com.example.Main.Inventory.controller;

import com.example.Main.Inventory.service.InventoryService;
import com.example.Main.Inventory.service.InventoryServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {
    private  final InventoryService inventoryService;

    @Autowired
    private Tracer tracer;
    public InventoryController(InventoryServiceImpl inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/inventory")
    public ResponseEntity<JsonNode> getInventory(@RequestHeader("Authorization") String auth) {
        Span span = tracer.spanBuilder("login-span").startSpan();
        span.setAttribute("my-attribute", "value");
        // Your business logic here
        span.end();
        return inventoryService.getInventory(auth);
    }

}
