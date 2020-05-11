package com.springboot.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class ExampleControllerTest {

    @Test
    void homePathTest(@Autowired MockMvc mockMvc) throws Exception {
        mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(content().json("{\"message\": \"Hello World!\"}"));
    }
}
