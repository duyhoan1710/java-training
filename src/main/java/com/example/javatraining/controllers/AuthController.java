package com.example.javatraining.controllers;


import com.example.javatraining.dtos.auth.request.LoginDto;
import com.example.javatraining.dtos.auth.request.RegisterDto;
import com.example.javatraining.dtos.auth.response.LoginResponse;
import com.example.javatraining.dtos.common.response.ResponseData;
import com.example.javatraining.services.AuthService;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Value
@RestController()
public class AuthController {
    AuthService authService;

    @PostMapping("auth/login")
    ResponseData<LoginResponse> login(@RequestBody @Valid LoginDto payload) {
        return this.authService.login(payload);
    }

    @PostMapping("auth/register")
    void register(@RequestBody @Valid RegisterDto payload) {
        this.authService.register(payload);
    }
}
