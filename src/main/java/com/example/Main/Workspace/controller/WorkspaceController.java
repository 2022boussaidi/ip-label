package com.example.Main.Workspace.controller;

import com.example.Main.Workspace.service.WorkspaceService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkspaceController {
    private  final WorkspaceService workspaceService;

    @Autowired
    public   WorkspaceController (WorkspaceService workspaceService){
        this.workspaceService= workspaceService;
    }
    @GetMapping("/workspaces")
    public ResponseEntity<JsonNode> getWorkspace(@RequestHeader("Authorization")String auth) {
        return workspaceService.getWorkspace(auth);
    }
    @GetMapping("/workspaces/{workspaceId}")
    public ResponseEntity<JsonNode> getWorkspaceById(@RequestHeader("Authorization") String auth,@PathVariable String workspaceId) {
        return workspaceService.getWorkspaceById(auth,workspaceId);
    }

}
