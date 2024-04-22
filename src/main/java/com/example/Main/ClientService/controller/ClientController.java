package com.example.Main.ClientService.controller;

import com.example.Main.ClientService.service.ClientService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    private  final ClientService  clientService;

    @Autowired
    public   ClientController (ClientService  clientService){
        this.clientService = clientService ;
    }
    @PostMapping("/clients")
    public ResponseEntity<JsonNode> client(@RequestHeader("Authorization")String auth) {
        return clientService.getClient(auth);
    }
    @GetMapping("/clients/{clientId}")
    public ResponseEntity<JsonNode> getClientById(@RequestHeader("Authorization") String auth,@PathVariable String clientId) {
        return clientService.getClientById(auth,clientId);
    }
}
