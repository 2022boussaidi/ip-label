package com.example.Main.Auth.service;



import com.example.Main.Auth.controller.LoginController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TokenService {
    private static final Logger LOG = Logger.getLogger(TokenService.class.getName());
    public String extractAccessToken(String responseBody) {
        try {
            LOG.log(Level.INFO, "Token service  is calling");

            // Parse the JSON response using Jackson ObjectMapper
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);

            // Extract the token from the "token" field
            JsonNode tokenNode = root.get("token");
            if (tokenNode != null) {
                return tokenNode.asText();
            } else {
                throw new IllegalArgumentException("Token not found in the response.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract access token from the response.", e);
        }
    }
}
