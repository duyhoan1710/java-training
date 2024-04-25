package com.example.javatraining.dtos.user.request;

import com.example.javatraining.enums.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateUserDto {
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty
    String email;

    @NotEmpty
    @Pattern(regexp = "^(0|\\+84)\\d{9,10}$", message = "Phone is invalid")
    String phone;

    @NotEmpty
    String name;

    @NotEmpty
    String password;

    @NotNull
    RoleEnum role;
}

