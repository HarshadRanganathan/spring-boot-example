package com.springboot.example.contractTest;

import com.springboot.example.service.BackendService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public abstract class BaseTestClass {

    @MockBean
    private BackendService backendService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(this.webApplicationContext);
        Mockito.when(backendService.backendCall()).thenReturn(new ResponseEntity<String>("{\"kind\": \"customsearch\"}", HttpStatus.OK));
    }
}
