package com.example.Main.Inventory.controller;

import com.example.Main.Auth.service.TokenService;
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

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class InventoryController {
    private  final InventoryService inventoryService;

    private static final Logger LOG = Logger.getLogger(TokenService.class.getName());

    public InventoryController(InventoryServiceImpl inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/inventory")
    public ResponseEntity<JsonNode> getInventory(@RequestHeader("Authorization") String auth) {
        LOG.log(Level.INFO, "Inventory controller  is calling");
        return inventoryService.getInventory(auth);
    }

}
