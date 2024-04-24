package com.example.Main.Client.controller;

import com.example.Main.Client.service.ClientService;
import com.example.Main.Client.service.ClientServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    private  final ClientService clientService;

    @Autowired
    public   ClientController (ClientServiceImpl clientService){
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
