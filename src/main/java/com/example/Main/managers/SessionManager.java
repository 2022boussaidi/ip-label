package com.example.Main.managers;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@SessionScope
public class SessionManager {
    private String accessToken;
    private static final Logger LOG = Logger.getLogger(SessionManager.class.getName());


    public String getAccessToken() {
        LOG.log(Level.INFO, "get inventory is calling");
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
