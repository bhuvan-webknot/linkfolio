package com.bhuvan.linkfolio.service;


import com.bhuvan.linkfolio.dto.UserLoginDto;
import com.bhuvan.linkfolio.dto.UserRequestDto;
import com.bhuvan.linkfolio.dto.UserResponseDto;
import com.bhuvan.linkfolio.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    UserResponseDto registerUser(UserRequestDto user);
    UserResponseDto loginUser(UserLoginDto user);
    User getUserDetails(String username);
    String generateVerificationToken(UserRequestDto registeredUser);
}
