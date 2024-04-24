package com.example.Main.Application.controller;



import com.example.Main.Application.service.ApplicationService;
import com.example.Main.Application.service.ApplicationServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {
    private  final ApplicationService applicationService;

    @Autowired
    public   ApplicationController (ApplicationService applicationService){
        this.applicationService= applicationService;
    }
    @GetMapping("/applications")
    public ResponseEntity<JsonNode> getApplication(@RequestHeader("Authorization")String auth) {
        return applicationService.getApplication(auth);
    }
    @GetMapping("/applicationss/{applicationId}")
    public ResponseEntity<JsonNode> getApplicationById(@RequestHeader("Authorization") String auth,@PathVariable String applicationId) {
        return applicationService.getApplicationById(auth,applicationId);
    }
    @GetMapping("applications/{workspaceId}")
    public ResponseEntity<JsonNode> getApplicationByWorkspace(@RequestHeader("Authorization") String auth,@PathVariable String workspaceId) {
        return applicationService.getApplicationByWorkspace(auth,workspaceId);
    }
}
