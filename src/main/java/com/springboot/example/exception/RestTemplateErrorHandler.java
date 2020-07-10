package com.springboot.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateErrorHandler extends DefaultResponseErrorHandler {
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return (
                clientHttpResponse.getStatusCode().series() == CLIENT_ERROR ||
                        clientHttpResponse.getStatusCode().series() == SERVER_ERROR
        );
    }

    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        if (clientHttpResponse.getStatusCode().series() == SERVER_ERROR) {
            throw new RuntimeException("Runtime Error");
        } else if (clientHttpResponse.getStatusCode().series() == CLIENT_ERROR) {
            // handle CLIENT_ERROR
            if (clientHttpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RuntimeException("Backend endpoint is not available");
            } else {
                throw new RuntimeException("Backend service error");
            }
        }
    }
}
