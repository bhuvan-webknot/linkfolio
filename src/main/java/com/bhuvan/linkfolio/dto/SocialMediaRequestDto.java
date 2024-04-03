package com.bhuvan.linkfolio.dto;

import com.bhuvan.linkfolio.model.SocialMediaName;
import lombok.Getter;

@Getter
public class SocialMediaRequestDto {
    private SocialMediaName name;
    private String link;
}
