package com.example.javatraining.services;


import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.user.request.CreateUserDto;
import com.example.javatraining.dtos.user.request.ListUserQueryDto;
import com.example.javatraining.dtos.user.request.ResetPasswordDto;
import com.example.javatraining.dtos.user.response.UserResponse;

public interface UserService {
    ResponsePagination<UserResponse> listUsers(ListUserQueryDto query);

    void createUser(CreateUserDto payload);

    void resetPassword(long userId, ResetPasswordDto payload);
}
