package com.example.Main.controllers;

import com.example.Main.services.WorkspaceService;
import com.example.Main.services.ZoneService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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
}
