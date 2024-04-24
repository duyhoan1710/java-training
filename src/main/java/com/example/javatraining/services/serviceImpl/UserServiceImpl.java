package com.example.javatraining.services.serviceImpl;


import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.user.request.CreateUserDto;
import com.example.javatraining.dtos.user.request.ListUserQueryDto;
import com.example.javatraining.dtos.user.request.ResetPasswordDto;
import com.example.javatraining.dtos.user.request.UpdateUserDto;
import com.example.javatraining.dtos.user.response.UserResponse;
import com.example.javatraining.entities.User;
import com.example.javatraining.exceptions.ErrorCode;
import com.example.javatraining.exceptions.ErrorException;
import com.example.javatraining.repositories.UserRepository;
import com.example.javatraining.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponsePagination<UserResponse> listUsers(ListUserQueryDto query) {
        final Pageable pageable = PageRequest.of(query.getPage() - 1, query.getLimit());

        Page<User> users = userRepository.findByNameLike(query.getName(), pageable);

        return new ResponsePagination<>(
                query.getPage(),
                query.getLimit(),
                users.getTotalElements(),
                users.getContent().stream().map(UserResponse::from).toList());
    }

    public void createUser(CreateUserDto payload) {
        User user = this.userRepository.findByEmailOrPhone(payload.getEmail(), payload.getPhone()).orElse(null);

        if (user != null) {
            throw new ErrorException(ErrorCode.DUPLICATE_USER);
        }

        User newUser = new User();
        newUser.setEmail(payload.getEmail());
        newUser.setPhone(payload.getPhone());
        newUser.setName(payload.getName());
        newUser.setPassword(passwordEncoder.encode(payload.getPassword()));
        newUser.setRole(payload.getRole());

        userRepository.save(newUser);
    }

    public void updateUser(long userId, UpdateUserDto payload) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ErrorException(ErrorCode.USER_NOT_FOUND));

        user.setName(payload.getName());

        this.userRepository.save(user);
    }

    public void resetPassword(long userId, ResetPasswordDto payload) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ErrorException(ErrorCode.USER_NOT_FOUND));

        user.setPassword(passwordEncoder.encode(payload.getPassword()));

        this.userRepository.save(user);
    }
}
