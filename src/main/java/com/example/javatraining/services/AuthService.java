package com.example.javatraining.services;


import com.example.javatraining.dtos.auth.request.LoginDto;
import com.example.javatraining.dtos.auth.request.RegisterDto;
import com.example.javatraining.dtos.auth.response.LoginResponse;
import com.example.javatraining.dtos.common.response.ResponseData;

public interface AuthService {
    ResponseData<LoginResponse> login(LoginDto payload);

    void register(RegisterDto payload);
}
