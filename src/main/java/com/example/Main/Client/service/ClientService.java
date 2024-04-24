package com.example.Main.Client.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface ClientService {

    ResponseEntity<JsonNode> getClient(String auth);
    ResponseEntity<JsonNode> getClientById(String auth, String ClientId);

}
