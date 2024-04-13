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
    ResponsePagination <UserResponse> listUsers (@RequestParam(required = true) int page, @RequestParam(required = true) int limit, @RequestParam(required = false) String fullName) {
       return this.userService.listUsers(new ListUserQueryDto(page, limit, fullName));
    }

    @PutMapping("users/{userId}")
    void updateUser (@PathVariable long userId, @RequestBody @Valid UpdateUserDto payload) {
        this.userService.updateUser(payload, userId);
    }
}
