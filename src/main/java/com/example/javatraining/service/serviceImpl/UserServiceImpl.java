package com.example.javatraining.service.serviceImpl;

import com.example.javatraining.common.exception.ErrorCode;
import com.example.javatraining.common.exception.ErrorException;
import com.example.javatraining.common.response.ResponsePagination;
import com.example.javatraining.dto.user.request.ListUserQueryDto;
import com.example.javatraining.dto.user.request.UpdateUserDto;
import com.example.javatraining.dto.user.response.UserResponse;
import com.example.javatraining.entity.User;
import com.example.javatraining.repository.UserRepository;
import com.example.javatraining.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public ResponsePagination<UserResponse> listUsers (ListUserQueryDto query) {
        final Pageable pageable = PageRequest.of(query.getPage() - 1, query.getLimit());

        Page<User> users = this.userRepository.findByFullNameLike(query.getFullName(), pageable);

        return new ResponsePagination<>(
                query.getPage(),
                query.getLimit(),
                users.getTotalElements(),
                users.getContent().stream().map(UserResponse::from).toList()
        );
    }

    public void updateUser (UpdateUserDto payload, long userId) {
        User user = this.userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new ErrorException(ErrorCode.USER_NOT_FOUND);
        }

        user.setFullName(payload.getFullName());

        this.userRepository.save(user);
    }

}
