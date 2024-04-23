package com.example.Main.Auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    // Getters and setters
}
