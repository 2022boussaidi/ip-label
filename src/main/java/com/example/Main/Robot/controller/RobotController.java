package com.example.Main.Robot.controller;

import com.example.Main.Robot.service.RobotService;
import com.example.Main.Robot.service.RobotServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController


public class RobotController {

    private final RobotService robotService;
    private static final Logger LOG = Logger.getLogger(RobotController.class.getName());


    @Autowired
    public RobotController(RobotService robotService) {
        this.robotService = robotService;
    }

    @PostMapping("/callrobots")
    public ResponseEntity<JsonNode> getAllRobots(@RequestHeader("Authorization") String auth) {
        LOG.log(Level.INFO, "callrobots is calling");
        return robotService.getAllRobots(auth);
    }

    @GetMapping("/callrobot/{robotId}")
    public ResponseEntity<JsonNode> getRobot(@RequestHeader("Authorization") String auth,@PathVariable String robotId) {
        LOG.log(Level.INFO, "get robot by id  is calling");
        return robotService.getRobot(auth,robotId);
    }

    @PostMapping("/reboot/{robotId}")
    public ResponseEntity<JsonNode> rebootRobot(@RequestHeader("Authorization") String auth,@PathVariable String robotId) {
        LOG.log(Level.INFO, "rebbot robot by id is calling");
        return robotService.rebootRobot(auth,robotId);
    }

   /**@GetMapping("/robot/{robotId}/queues")=>>>< get queuues by robot id
    public ResponseEntity<JsonNode> getAllQueuesByRobotId(@PathVariable String robotId) {
        return robotService.getAllQueuesByRobotId(robotId);
    }**/
}
