package com.springboot.example.service;

import com.springboot.example.properties.ExampleProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BackendService {

    private final RestTemplate restTemplate;

    private final ExampleProperties exampleProperties;

    public ResponseEntity<String> backendCall() {
        return this.restTemplate.getForEntity(exampleProperties.getBackend().getUrl(), String.class);
    }
}
