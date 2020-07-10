package com.springboot.example.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CustomError {

    private final String errorCode;
    private final String errorMessage;

}
