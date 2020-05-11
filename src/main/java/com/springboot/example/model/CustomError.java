package com.springboot.example.model;

public class CustomError {

    private String errorCode;
    private String errorMessage;

    public CustomError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
