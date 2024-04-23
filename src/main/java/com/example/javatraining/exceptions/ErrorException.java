package com.example.javatraining.exceptions;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorException extends RuntimeException {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private int errorCode;
    private HttpStatus statusCode;
    private String message;
    private String debugMessage;

    private ErrorException() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorException(ErrorCode errorCode) {
        this();
        this.errorCode = errorCode.getErrorCode();
        this.statusCode = errorCode.getStatusCode();
        this.message = errorCode.getMessage();
    }

    public ErrorException(Throwable ex) {
        this();
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }
}
