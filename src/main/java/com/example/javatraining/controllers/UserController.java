package com.example.javatraining.controllers;


import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.user.request.ListUserQueryDto;
import com.example.javatraining.dtos.user.request.UpdateUserDto;
import com.example.javatraining.dtos.user.response.UserResponse;
import com.example.javatraining.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PutMapping("users/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    void updateUser(@PathVariable long userId, @RequestBody @Valid UpdateUserDto payload) {
        this.userService.updateUser(payload, userId);
    }
}
