package com.springboot.example.contractTest;

import com.springboot.example.service.BackendService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseTestClass {

    @LocalServerPort
    int port;

    @MockBean
    private BackendService backendService;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:" + this.port;
        Mockito.when(backendService.backendCall()).thenReturn(new ResponseEntity<String>("{\"kind\": \"customsearch\"}", HttpStatus.OK));
    }
}
