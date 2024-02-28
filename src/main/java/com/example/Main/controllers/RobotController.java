package com.example.Main.controllers;

import com.example.Main.services.RobotService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class RobotController {

    private final RobotService robotService;

    @Autowired
    public RobotController(RobotService robotService) {
        this.robotService = robotService;
    }

    @PostMapping("/callrobots")
    public ResponseEntity<JsonNode> getAllRobots(@RequestHeader("Authorization") String auth) {
        return robotService.getAllRobots(auth);
    }

    @GetMapping("/callrobot/{robotId}")
    public ResponseEntity<JsonNode> getRobot(@RequestHeader("Authorization") String auth,@PathVariable String robotId) {
        return robotService.getRobot(auth,robotId);
    }

    @PostMapping("/reboot/{robotId}")
    public ResponseEntity<JsonNode> rebootRobot(@RequestHeader("Authorization") String auth,@PathVariable String robotId) {
        return robotService.rebootRobot(auth,robotId);
    }

   /**@GetMapping("/robot/{robotId}/queues")=>>>< get queuues by robot id
    public ResponseEntity<JsonNode> getAllQueuesByRobotId(@PathVariable String robotId) {
        return robotService.getAllQueuesByRobotId(robotId);
    }**/
}
