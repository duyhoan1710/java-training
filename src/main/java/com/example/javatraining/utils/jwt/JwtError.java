package com.example.javatraining.utils.jwt.error;


import org.springframework.security.core.AuthenticationException;

public class InvalidJwtError extends AuthenticationException {
    public InvalidJwtError() {
        super("Invalid jwt error");
    }
}
