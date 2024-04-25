package com.example.javatraining.controllers;


import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.user.request.CreateUserDto;
import com.example.javatraining.dtos.user.request.ListUserQueryDto;
import com.example.javatraining.dtos.user.request.ResetPasswordDto;
import com.example.javatraining.dtos.user.response.UserResponse;
import com.example.javatraining.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController()
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN') OR hasRole('OPERATOR')")
    @GetMapping("users")
    ResponsePagination<UserResponse> listUsers(
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int limit,
            @RequestParam(required = false, defaultValue = "") String name) {
        return this.userService.listUsers(new ListUserQueryDto(page, limit, name));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("users")
    void createUser(@Valid @RequestBody CreateUserDto payload) {
        this.userService.createUser(payload);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("users/{userId}/reset-password")
    void resetPassword(@PathVariable long userId, @Valid @RequestBody ResetPasswordDto payload) {
        this.userService.resetPassword(userId, payload);
    }
}
