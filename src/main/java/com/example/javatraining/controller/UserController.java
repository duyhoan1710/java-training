package com.example.javatraining.controller;

import com.example.javatraining.common.response.ResponsePagination;
import com.example.javatraining.dto.user.request.ListUserQueryDto;
import com.example.javatraining.dto.user.request.UpdateUserDto;
import com.example.javatraining.dto.user.response.UserResponse;
import com.example.javatraining.service.UserService;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Value
public class UserController {
    UserService userService;

    @GetMapping("users")
    ResponsePagination <UserResponse> listUsers (@RequestParam ListUserQueryDto query) {
       return this.userService.listUsers(query);
    }

    @PutMapping("users/{userId}")
    void updateUser (@PathVariable long userId, @RequestBody @Valid UpdateUserDto payload) {
        this.userService.updateUser(payload, userId);
    }
}
