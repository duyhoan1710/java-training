package com.example.javatraining.dtos.user.request;

import com.example.javatraining.enums.RoleEnum;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateUserDto {
    @NotEmpty
    String email;

    @NotEmpty
    String phone;

    @NotEmpty
    String name;

    @NotEmpty
    String password;

    @NotEmpty
    RoleEnum role;
}

