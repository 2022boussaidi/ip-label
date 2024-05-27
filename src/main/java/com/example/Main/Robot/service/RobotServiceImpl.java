package com.example.Main.Robot.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
@EnableEurekaClient
@Service
public class RobotServiceImpl  implements  RobotService{

    private final RestTemplate restTemplate;
    private static final Logger LOG = Logger.getLogger(RobotServiceImpl.class.getName());

    @Autowired
    public RobotServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<JsonNode> getAllRobots(String auth) {
        LOG.log(Level.INFO, "get robots is calling");

        String apiUrl = "https://ekara.ip-label.net/infra-api/robots";
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }

    public ResponseEntity<JsonNode> getRobot(String auth, String robotId) {
        LOG.log(Level.INFO, "get robot by id  is calling");

        String apiUrl = "https://ekara.ip-label.net/infra-api/robot/" + robotId;
        HttpMethod method = HttpMethod.GET;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }
    public ResponseEntity<JsonNode> rebootRobot(String auth, String robotId) {
        LOG.log(Level.INFO, "reboot robot by id  is calling");

        String apiUrl = "https://ekara.ip-label.net/infra-api/robot/" + robotId + "/reboot";
        HttpMethod method = HttpMethod.POST;
        String accessToken = extractToken(auth);
        HttpHeaders headers = createHeaders(accessToken);
        return executeRequest(apiUrl, method, headers, JsonNode.class);
    }

    private String extractToken(String authorizationHeader) {
        LOG.log(Level.INFO, "extract token  is calling");

        String[] parts = authorizationHeader.split(" ");
        if (parts.length == 2 && parts[0].equalsIgnoreCase("Bearer")) {
            return parts[1];
        } else {
            throw new IllegalArgumentException("Invalid Authorization header format");
        }
    }

    private HttpHeaders createHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    private ResponseEntity<JsonNode> executeRequest(String apiUrl, HttpMethod method, HttpHeaders headers, Class<JsonNode> responseType) {
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        LOG.log(Level.INFO, "execute request  is calling");

        try {
            return restTemplate.exchange(apiUrl, method, requestEntity, responseType);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getRawStatusCode()).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
