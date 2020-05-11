package com.springboot.example.controller;

import com.springboot.example.exception.CustomException;
import io.micrometer.core.annotation.Timed;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
    ResponseEntity<?> home() {
        return new ResponseEntity<Object>("{\"message\": \"Hello World!\"}", HttpStatus.OK);
    }

    @RequestMapping("/err")
    ResponseEntity<?> error() throws CustomException { throw new CustomException("Server error"); }
}
