package com.example.javatraining.service;

import com.example.javatraining.common.response.ResponseData;
import com.example.javatraining.common.response.ResponsePagination;
import com.example.javatraining.dto.user.request.ListUserQueryDto;
import com.example.javatraining.dto.user.request.UpdateUserDto;
import com.example.javatraining.dto.user.response.UserResponse;

public interface UserService {
    ResponsePagination<UserResponse> listUsers(ListUserQueryDto query);

    void updateUser (UpdateUserDto payload, long userId);
}
