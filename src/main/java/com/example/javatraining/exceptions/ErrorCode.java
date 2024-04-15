package com.example.javatraining.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DATABASE(0, HttpStatus.INTERNAL_SERVER_ERROR, "A database error has occurred."),
    DUPLICATE_USER(1, HttpStatus.BAD_REQUEST, "This user already exists."),
    USER_NOT_FOUND(2, HttpStatus.NOT_FOUND, "User not found");

    private final int errorCode;
    private final HttpStatus statusCode;
    private final String message;
}
