package com.springboot.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateErrorHandler extends DefaultResponseErrorHandler {
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return (
                clientHttpResponse.getStatusCode().is4xxClientError() ||
                        clientHttpResponse.getStatusCode().is5xxServerError()
        );
    }

    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        if (clientHttpResponse.getStatusCode().is5xxServerError()) {
            throw new RuntimeException("Runtime Error");
        } else if (clientHttpResponse.getStatusCode().is4xxClientError()) {
            // handle CLIENT_ERROR
            if (clientHttpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RuntimeException("Backend endpoint is not available");
            } else {
                throw new RuntimeException("Backend service error");
            }
        }
    }
}
