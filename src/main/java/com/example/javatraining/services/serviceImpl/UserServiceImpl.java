package com.example.javatraining.services.serviceImpl;


import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.user.request.ListUserQueryDto;
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
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public ResponsePagination<UserResponse> listUsers(ListUserQueryDto query) {
        final Pageable pageable = PageRequest.of(query.getPage() - 1, query.getLimit());

        Page<User> users = this.userRepository.findAllByFullNameLike(query.getFullName(), pageable);

        return new ResponsePagination<>(
                query.getPage(),
                query.getLimit(),
                users.getTotalElements(),
                users.getContent().stream().map(UserResponse::from).toList());
    }

    public void updateUser(UpdateUserDto payload, long userId) {
        User user = this.userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new ErrorException(ErrorCode.USER_NOT_FOUND);
        }

        user.setFullName(payload.getFullName());

        this.userRepository.save(user);
    }
}
