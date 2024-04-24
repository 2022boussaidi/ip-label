package com.example.Main.Worker.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface QueueService {
    ResponseEntity<JsonNode> getAllQueues(String auth);

    ResponseEntity<JsonNode> getQueue(String auth, String queueId);
    ResponseEntity<JsonNode> reserveQueue(String auth, String queueId);
    ResponseEntity<JsonNode> resetQueue(String auth,String queueId);
    ResponseEntity<JsonNode> startQueue(String auth ,  String queueId);
    ResponseEntity<JsonNode> stopQueue(String auth , String queueId);
}


