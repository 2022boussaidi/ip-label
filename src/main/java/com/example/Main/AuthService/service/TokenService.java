package com.example.Main.AuthService.service;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String extractAccessToken(String responseBody) {
        try {
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
