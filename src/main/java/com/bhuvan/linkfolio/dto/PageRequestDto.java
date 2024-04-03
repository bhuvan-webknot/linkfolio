package com.bhuvan.linkfolio.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PageRequestDto {
    private Long id;
    private String displayName;
    private String bio;
    private Long userId;
    private String backgroundType;
    private String backgroundImageUrl;
    private String backgroundColor;
    private List<SocialMediaRequestDto> buttons;
    private List<LinkRequestDto> links;
}
