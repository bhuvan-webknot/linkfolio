package com.bhuvan.linkfolio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class UserRequestDto {
    private String fullname;
    private String username;
    private String email;
    private String password;
}
