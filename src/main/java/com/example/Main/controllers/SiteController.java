package com.example.Main.controllers;

import com.example.Main.services.SiteService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController


public class SiteController {

    private final SiteService siteService;

    @Autowired
    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @PostMapping("/callsites")
    public ResponseEntity<JsonNode> getAllSites(@RequestHeader("Authorization") String auth) {
        return siteService.getAllSites(auth);
    }

    @GetMapping("/callsite/{siteId}")
    public ResponseEntity<JsonNode> getQueueDetails(@RequestHeader("Authorization") String auth, @PathVariable("siteId") String siteId) {
        return siteService.getQueueDetails(auth, siteId);
    }
    @PatchMapping("/site/{siteId}/resetQueues")
    public ResponseEntity<JsonNode> resetQueues(@RequestHeader("Authorization") String auth, @PathVariable String siteId) {
        return siteService.resetQueues(auth, siteId);
    }
}
