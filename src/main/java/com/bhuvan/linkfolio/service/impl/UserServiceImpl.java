package com.bhuvan.linkfolio.service.impl;

import com.bhuvan.linkfolio.dto.UserLoginDto;
import com.bhuvan.linkfolio.dto.UserRequestDto;
import com.bhuvan.linkfolio.dto.UserResponseDto;
import com.bhuvan.linkfolio.exceptions.ResourceNotFoundException;
import com.bhuvan.linkfolio.model.Page;
import com.bhuvan.linkfolio.model.User;
import com.bhuvan.linkfolio.repository.PageRepository;
import com.bhuvan.linkfolio.repository.UserRepository;
import com.bhuvan.linkfolio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PageRepository pageRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponseDto registerUser(UserRequestDto user) {
        User newUser = User.builder()
                .fullname(user.getFullname())
                .username(user.getUsername().toLowerCase())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .isVerified(false)
                .build();


        userRepository.save(newUser);


        return UserResponseDto
                .builder()
                .fullname(newUser.getFullname())
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .isVerified(newUser.isVerified())
                .id(newUser.getId())
                .build();
    }

    @Override
    public UserResponseDto loginUser(UserLoginDto user) {
        User foundUser = userRepository.findByEmail(user.getLoginId())
                .orElseGet(() -> userRepository.findByUsername(user.getLoginId())
                        .orElseThrow(() -> new ResourceNotFoundException("No user exists with the provided id !!")));

        if(!passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            throw new ResourceNotFoundException("Password incorrect !!");
        }

        return UserResponseDto
                .builder()
                .fullname(foundUser.getFullname())
                .username(foundUser.getUsername())
                .email(foundUser.getEmail())
                .isVerified(foundUser.isVerified())
                .id(foundUser.getId())
                .build();
    }

    @Override
    public User getUserDetails(String username) {
        User foundUser = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Page page = foundUser.getPage();
        if (page != null) {
            page.setPageViewCount(page.getPageViewCount() + 1);
            pageRepository.save(page);
        }
        return foundUser;
    }

    public boolean verifyUser(String token) {
        Optional<User> user = userRepository.findByVerificationToken(token);
        if (user.isPresent()) {
            User user1 = user.get();
            user1.setVerified(true);
            userRepository.save(user1);
            return true;
        }
        return false;
    }

    public String generateVerificationToken(UserRequestDto registeredUser) {
            String token = UUID.randomUUID().toString();
            User foundUser = userRepository.findByEmail(registeredUser.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
            foundUser.setVerificationToken(token);
            userRepository.save(foundUser);
            return token;
    }
}
