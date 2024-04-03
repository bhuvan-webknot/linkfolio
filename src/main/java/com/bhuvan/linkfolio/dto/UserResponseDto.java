package com.bhuvan.linkfolio.dto;

import com.bhuvan.linkfolio.model.Page;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponseDto {
    private Long id;
    private String fullname;
    private String username;
    private String email;
    private boolean isVerified;
}
