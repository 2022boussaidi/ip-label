package com.example.Main.controllers;

import com.example.Main.services.QueueService;
import com.example.Main.services.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private  final UserService userService;

    @Autowired
    public  UserController (UserService userService){
        this.userService = userService ;
    }
    @PostMapping("/users")
    public ResponseEntity<JsonNode> User(@RequestHeader("Authorization")String auth) {
        return userService.getUser(auth);
    }
    @GetMapping("/current")
    public ResponseEntity<JsonNode> currentUser(@RequestHeader("Authorization")String auth) {
        return userService.currentUser(auth);
    }
}
