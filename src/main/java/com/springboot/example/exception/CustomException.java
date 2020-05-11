package com.springboot.example.exception;

public class CustomException extends Exception {
    public CustomException(String errorMessage) {
        super(errorMessage);
    }
    public CustomException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
