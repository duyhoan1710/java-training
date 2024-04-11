package com.example.javatraining.dto.user.response;

import com.example.javatraining.dto.response.StudentResponse;
import com.example.javatraining.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String fullName;
    private String email;

    public static UserResponse from(final User user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .build();
    }
}
