package com.springboot.example.exception;

import com.springboot.example.model.CustomError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class RestControllerAdvice extends ResponseEntityExceptionHandler  {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(RuntimeException runtimeException, HttpServletRequest httpServletRequest) {
        log.error(runtimeException.getMessage(), runtimeException);
        HttpStatus status = getStatus(httpServletRequest);
        return new ResponseEntity<Object>(new CustomError("1000", "Server error"), status);
    }

    @ExceptionHandler
    @ResponseBody
    ResponseEntity<?> handleControllerException(Exception exception, HttpServletRequest httpServletRequest) {
        log.error(exception.getMessage(), exception);
        HttpStatus status = getStatus(httpServletRequest);
        return new ResponseEntity<Object>(new CustomError("1000", "Server error"), status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
