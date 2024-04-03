package com.bhuvan.linkfolio.controller;


import com.bhuvan.linkfolio.dto.UserLoginDto;
import com.bhuvan.linkfolio.dto.UserRequestDto;
import com.bhuvan.linkfolio.dto.UserResponseDto;
import com.bhuvan.linkfolio.model.User;
import com.bhuvan.linkfolio.service.impl.UserServiceImpl;
import com.bhuvan.linkfolio.utils.SendVerificationEmail;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private SendVerificationEmail emailService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserRequestDto user){

        UserResponseDto registeredUser = userService.registerUser(user);
        String verificationToken = userService.generateVerificationToken(user);
        try {
            emailService.sendEmail(registeredUser.getEmail(), verificationToken);
            return ResponseEntity.ok("Registration successful. Please check your email for verification.");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send verification email.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> loginUser(@RequestBody UserLoginDto user){
        return ResponseEntity.ok(userService.loginUser(user));
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam("token") String token){
        if(userService.verifyUser(token))
            return ResponseEntity.ok("User Verified Successfully");
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Verification Failed");
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByName(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserDetails(username));
    }
}
