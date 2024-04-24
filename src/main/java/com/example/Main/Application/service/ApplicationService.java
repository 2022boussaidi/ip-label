package com.example.Main.Application.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface ApplicationService {
    ResponseEntity<JsonNode> getApplication(String auth);
    ResponseEntity<JsonNode> getApplicationById(String auth, String applicationId);
    ResponseEntity<JsonNode> getApplicationByWorkspace(String auth, String workspaceId);
}
