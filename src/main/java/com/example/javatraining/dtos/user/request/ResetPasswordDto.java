package com.example.javatraining.dtos.user.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResetPasswordDto {
    @NotEmpty
    String password;
}
