package com.example.Main.ConfigClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class ConfigClientController {

        @Value("${message:Hello default}")
        private String message;

        @GetMapping("/message")
        public String getMessage() {
            return message;
        }

    }

