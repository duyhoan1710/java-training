package com.example.javatraining.dtos.auth.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Value;

@Value
public class RegisterDto {
    @Email
    @NotEmpty
    String email;

    @NotEmpty
    String phone;

    @NotEmpty
    String name;

    @NotEmpty
    String password;
}
