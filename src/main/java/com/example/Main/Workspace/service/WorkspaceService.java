package com.example.Main.Workspace.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface WorkspaceService {
    ResponseEntity<JsonNode> getWorkspace(String auth);
    ResponseEntity<JsonNode> getWorkspaceById(String auth ,String workspaceId);

}
