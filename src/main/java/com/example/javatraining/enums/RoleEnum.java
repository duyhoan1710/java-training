package com.example.javatraining.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {
    ADMIN("ADMIN"),
    OPERATOR("OPERATOR");

    private final String value;
}
