package com.springboot.example.controller;

import com.springboot.example.exception.CustomException;
import com.springboot.example.service.BackendService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ExampleController {

    private final BackendService backendService;

    @Autowired
    public ExampleController(BackendService backendService) {
        this.backendService = backendService;
    }

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
    ResponseEntity<?> home() {
        log.debug("Request received");
        return new ResponseEntity<Object>("{\"message\": \"Hello World!\"}", HttpStatus.OK);
    }

    @RequestMapping(value = "/backend", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
    ResponseEntity<?> backend() {
        log.debug("Request received");
        final ResponseEntity<String> response = backendService.backendCall();
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @RequestMapping("/err")
    ResponseEntity<?> error() throws CustomException { throw new CustomException("Server error"); }
}
