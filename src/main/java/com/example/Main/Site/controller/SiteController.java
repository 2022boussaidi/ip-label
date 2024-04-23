package com.example.Main.Site.controller;

import com.example.Main.Site.service.SiteService;
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
    //GET available site for scripting ( Site is available if he has at least on queue reserved for scripting)
    @GetMapping("/scripting")
    public ResponseEntity<JsonNode> getScripting(@RequestHeader("Authorization") String auth) {
        return siteService.getScripting(auth);
    }
    //get zones and scenarios per sites
    @GetMapping("/linked-elements/{siteId}")
    public ResponseEntity<JsonNode> getLinkedElements(@RequestHeader("Authorization") String auth,@PathVariable("siteId") String siteId) {
        return siteService.getLinkedElements(auth,siteId);
    }

}
