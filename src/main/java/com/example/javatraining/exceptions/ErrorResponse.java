package com.example.javatraining.exceptions;


import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int errorCode;
    private HttpStatus statusCode;
    private String message;
    private String debugMessage;


    public ErrorResponse(
            int errorCode, HttpStatus statusCode, String message, String debugMessage, LocalDateTime timestamp) {
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        this.message = message;
        this.debugMessage = debugMessage;
    }
}
