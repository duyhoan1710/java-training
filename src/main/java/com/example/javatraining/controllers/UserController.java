package com.example.javatraining.controllers;


import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.user.request.ListUserQueryDto;
import com.example.javatraining.dtos.user.request.UpdateUserDto;
import com.example.javatraining.dtos.user.response.UserResponse;
import com.example.javatraining.services.UserService;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Value
public class UserController {
    UserService userService;

    @GetMapping("users")
    ResponsePagination<UserResponse> listUsers(
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int limit,
            @RequestParam(required = false) String fullName) {
        return this.userService.listUsers(new ListUserQueryDto(page, limit, fullName));
    }

    @PutMapping("users/{userId}")
    void updateUser(@PathVariable long userId, @RequestBody @Valid UpdateUserDto payload) {
        this.userService.updateUser(payload, userId);
    }
}
