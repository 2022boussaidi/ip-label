package com.example.Main.Robot.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface RobotService {
    ResponseEntity<JsonNode> getAllRobots(String auth);
    ResponseEntity<JsonNode> getRobot(String auth, String robotId);
    ResponseEntity<JsonNode> rebootRobot(String auth,String robotId);
}
