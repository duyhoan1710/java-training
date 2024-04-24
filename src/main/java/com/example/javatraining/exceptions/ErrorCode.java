package com.example.javatraining.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    VALIDATION_ERROR(-1, HttpStatus.BAD_REQUEST, "Validate data error"),

    INTERNAL_SERVER_ERROR(0, HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error."),

    DUPLICATE_USER(1, HttpStatus.BAD_REQUEST, "This user already exists."),
    USER_NOT_FOUND(2, HttpStatus.NOT_FOUND, "User not found"),

    PASSWORD_INCORRECT(3, HttpStatus.BAD_REQUEST, "Password incorrect"),
    AUTHENTICATION_NOT_FOUND(4, HttpStatus.UNAUTHORIZED, "Authentication token not found"),
    AUTHENTICATION_ERROR(5, HttpStatus.UNAUTHORIZED, "Authentication error"),

    DUPLICATE_PRODUCT(6, HttpStatus.BAD_REQUEST, "This product already exists."),
    PRODUCT_NOT_FOUND(7, HttpStatus.NOT_FOUND, "Product not found"),
    DUPLICATE_PRODUCT_IN_ORDER(8, HttpStatus.BAD_REQUEST, "Duplicate product in order"),
    QUANTITY_NOT_ENOUGH(8, HttpStatus.BAD_REQUEST, "Quantity not enough"),

    CUSTOMER_NOT_FOUND(2, HttpStatus.NOT_FOUND, "Customer not found");


    private final int errorCode;
    private final HttpStatus statusCode;
    private final String message;
}
