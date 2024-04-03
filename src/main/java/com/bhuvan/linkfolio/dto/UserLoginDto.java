package com.bhuvan.linkfolio.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginDto {
    private String loginId; //Can be email or username
    private String password;
}
