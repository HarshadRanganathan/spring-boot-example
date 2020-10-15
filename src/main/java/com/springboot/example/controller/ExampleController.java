package com.springboot.example.controller;

import com.springboot.example.exception.CustomException;
import com.springboot.example.model.Person;
import com.springboot.example.service.BackendService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ExampleController {

    private final BackendService backendService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    ResponseEntity<?> home() {
        log.debug("Request received");
        return ResponseEntity.ok("{\"message\": \"Hello World!\"}");
    }

    @GetMapping(value = "/backend", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    ResponseEntity<?> backend() {
        log.debug("Request received");
        return backendService.backendCall();
    }

    @GetMapping(value = "/person")
    @Timed
    ResponseEntity<?> person() {
        log.debug("Request received");
        final Person person = new Person("Harshad", "Ranganathan");
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @GetMapping("/err")
    ResponseEntity<?> error() throws CustomException { throw new CustomException("Server error"); }
}
