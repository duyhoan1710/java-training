package com.example.javatraining.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorException> GlobalExceptionHandler (Exception e) {
        // casting the generic Exception e to CustomErrorException
        ErrorException error = (ErrorException) e;

        HttpStatus statusCode = error.getStatusCode();
        int errorCode = error.getErrorCode();
        String message = error.getMessage();
        String debugMessage = error.getDebugMessage();

        return new ResponseEntity<>(
            new ErrorException(errorCode, statusCode, message, debugMessage),
            statusCode
        );
    }
}
