package com.example.javatraining.dtos.auth.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Value;

@Value
public class LoginDto {
    @NotEmpty
    @Email
    String email;

    @NotBlank
    @NotEmpty
    String password;
}
