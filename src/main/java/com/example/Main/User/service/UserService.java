package com.example.Main.User.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<JsonNode> getUser(String auth);
    ResponseEntity<JsonNode> getUserById(String auth, String userId);
    ResponseEntity<JsonNode> currentUser(String auth);
    ResponseEntity<JsonNode> getCurrentAlert(String auth);

}
