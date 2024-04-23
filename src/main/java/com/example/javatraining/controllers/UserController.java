package com.example.javatraining.controllers;


import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.user.request.CreateUserDto;
import com.example.javatraining.dtos.user.request.ListUserQueryDto;
import com.example.javatraining.dtos.user.request.ResetPasswordDto;
import com.example.javatraining.dtos.user.request.UpdateUserDto;
import com.example.javatraining.dtos.user.response.UserResponse;
import com.example.javatraining.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

@RestController()
@SecurityRequirement(name = "bearerAuth")
@Value
public class UserController {
    UserService userService;

    @GetMapping("users")
    ResponsePagination<UserResponse> listUsers(
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int limit,
            @RequestParam(required = false) String name) {
        return this.userService.listUsers(new ListUserQueryDto(page, limit, name));
    }

    @PostMapping("users")
    void createUser(@RequestBody CreateUserDto payload) {
        this.userService.createUser(payload);
    }

    @PutMapping("users/{userId}")
    void updateUser(@PathVariable long userId, @RequestBody @Valid UpdateUserDto payload) {
        this.userService.updateUser(userId, payload);
    }

    @PutMapping("users/{userId}/reset-password")
    void resetPassword(@PathVariable long userId, @RequestBody @Valid ResetPasswordDto payload) {
        this.userService.resetPassword(userId, payload);
    }
}
