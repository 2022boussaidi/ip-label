package com.example.Main.User.controller;

import com.example.Main.User.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/users/{userId}")
    public ResponseEntity<JsonNode> getUserById(@RequestHeader("Authorization") String auth,@PathVariable String userId) {
        return userService.getUserById(auth,userId);
    }

    @GetMapping("/current")
    public ResponseEntity<JsonNode> currentUser(@RequestHeader("Authorization")String auth) {
        return userService.currentUser(auth);
    }
}
