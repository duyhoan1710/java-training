package com.example.javatraining.services.serviceImpl;


import com.example.javatraining.dtos.auth.request.LoginDto;
import com.example.javatraining.dtos.auth.response.LoginResponse;
import com.example.javatraining.dtos.common.response.ResponseData;
import com.example.javatraining.entities.User;
import com.example.javatraining.exceptions.ErrorCode;
import com.example.javatraining.exceptions.ErrorException;
import com.example.javatraining.repositories.UserRepository;
import com.example.javatraining.services.AuthService;
import com.example.javatraining.utils.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    private PrivateKey privateKey;

    public ResponseData<LoginResponse> login(LoginDto payload) {
        User user = this.userRepository.findByEmail(payload.getEmail()).orElse(null);

        if (user == null) {
            throw new ErrorException(ErrorCode.USER_NOT_FOUND);
        }

        if (!passwordEncoder.matches(payload.getPassword(), user.getPassword())) {
            throw new ErrorException(ErrorCode.PASSWORD_INCORRECT);
        }

        String accessToken = JwtUtil.generateAccessToken(user, privateKey);

        return new ResponseData<>(new LoginResponse(accessToken));
    }
}
