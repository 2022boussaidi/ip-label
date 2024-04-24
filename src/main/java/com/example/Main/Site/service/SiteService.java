package com.example.Main.Site.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface SiteService {
    ResponseEntity<JsonNode> getAllSites(String auth);
    ResponseEntity<JsonNode> getLinkedElements(String auth,String siteId);
    ResponseEntity<JsonNode> getQueueDetails(String auth, String siteId);
    ResponseEntity<JsonNode> getScripting(String auth);
    ResponseEntity<JsonNode> resetQueues(String auth, String siteId);
}
