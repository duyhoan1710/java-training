package com.example.javatraining.exceptions;


import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorResponse> GlobalExceptionHandler(Exception e) {
        // casting the generic Exception e to CustomErrorException
        ErrorException error = (ErrorException) e;

        LocalDateTime dateTime = error.getTimestamp();
        HttpStatus statusCode = error.getStatusCode();
        int errorCode = error.getErrorCode();
        String message = error.getMessage();
        String debugMessage = error.getDebugMessage();

        return new ResponseEntity<>(
                new ErrorResponse(errorCode, statusCode, message, debugMessage, dateTime), statusCode);
    }

    @ExceptionHandler({BindException.class, UnexpectedTypeException.class})
    public ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;

        String errorMessage = "Request không hợp lệ";
        String debugMessage = null;

        if (e.getBindingResult().hasErrors()) {
            errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
            debugMessage = e.getLocalizedMessage();
        }

        return new ResponseEntity<>(
                new ErrorResponse(
                        errorCode.getErrorCode(),
                        errorCode.getStatusCode(),
                        errorMessage,
                        debugMessage,
                        LocalDateTime.now()
                ),
                errorCode.getStatusCode());
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ErrorResponse> handleAuthenticationException(Exception ex) {
        ErrorCode errorCode = ErrorCode.AUTHENTICATION_ERROR;

        return new ResponseEntity<>(
                new ErrorResponse(
                        errorCode.getErrorCode(),
                        errorCode.getStatusCode(),
                        ex.getLocalizedMessage(),
                        null,
                        LocalDateTime.now()
                ),
                errorCode.getStatusCode());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(Exception ex) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;

        return new ResponseEntity<>(
                new ErrorResponse(
                        errorCode.getErrorCode(),
                        errorCode.getStatusCode(),
                        ex.getLocalizedMessage(),
                        null,
                        LocalDateTime.now()
                ),
                errorCode.getStatusCode());
    }
}
