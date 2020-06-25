package com.springboot.example.service;

import com.springboot.example.exception.RestTemplateErrorHandler;
import com.springboot.example.properties.ExampleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BackendService {

    private final RestTemplate restTemplate;

    private final ExampleProperties exampleProperties;

    @Autowired
    public BackendService(ExampleProperties exampleProperties, RestTemplate restTemplate) {
        this.exampleProperties = exampleProperties;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> backendCall() {
        return this.restTemplate.getForEntity(exampleProperties.getBackend().getUrl(), String.class);
    }
}
