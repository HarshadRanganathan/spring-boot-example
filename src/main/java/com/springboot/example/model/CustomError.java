package com.springboot.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomError {

    private final String errorCode;
    private final String errorMessage;

}
