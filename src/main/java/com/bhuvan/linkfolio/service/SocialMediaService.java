package com.bhuvan.linkfolio.service;

import com.bhuvan.linkfolio.dto.SocialMediaRequestDto;
import com.bhuvan.linkfolio.model.SocialMedia;

import java.util.List;

public interface SocialMediaService {
    void addSocialMedia(Long pageId, SocialMediaRequestDto socialMediaRequestDto);
    void deleteSocialMedia(Long pageId, Long socialMediaId);
    void updateSocialMedia(Long pageId, Long socialMediaId, SocialMediaRequestDto socialMediaRequestDto);
    List<SocialMedia> getSocialMedia(Long pageId);
    SocialMedia getSocialMediaById(Long pageId, Long socialMediaId);
}
