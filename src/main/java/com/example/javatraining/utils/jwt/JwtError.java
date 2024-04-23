package com.example.javatraining.utils.jwt;


import com.example.javatraining.exceptions.ErrorCode;
import org.springframework.security.core.AuthenticationException;

public class JwtError extends AuthenticationException {
    public JwtError(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
