package com.bhuvan.linkfolio.service.impl;

import com.bhuvan.linkfolio.dto.SocialMediaRequestDto;
import com.bhuvan.linkfolio.exceptions.ResourceNotFoundException;
import com.bhuvan.linkfolio.model.Page;
import com.bhuvan.linkfolio.model.SocialMedia;
import com.bhuvan.linkfolio.repository.PageRepository;
import com.bhuvan.linkfolio.repository.SocialMediaRepository;
import com.bhuvan.linkfolio.service.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SocialMediaImpl implements SocialMediaService {

    @Autowired
    private SocialMediaRepository socialMediaRepository;

    @Autowired
    private PageRepository pageRepository;

    @Override
    public void addSocialMedia(Long pageId, SocialMediaRequestDto socialMediaRequestDto) {
        Page page = pageRepository.findById(pageId).orElseThrow(() -> new ResourceNotFoundException("Page not found"));

        SocialMedia socialMedia = SocialMedia
                .builder()
                .name(socialMediaRequestDto.getName())
                .link(socialMediaRequestDto.getLink())
                .page(page)
                .iconClickCount(0)
                .build();

        socialMediaRepository.save(socialMedia);
    }

    @Override
    public void deleteSocialMedia(Long pageId, Long socialMediaId) {
        socialMediaRepository.deleteById(socialMediaId);
    }

    @Override
    public void updateSocialMedia(Long pageId, Long socialMediaId, SocialMediaRequestDto socialMediaRequestDto) {

    }

    @Override
    public List<SocialMedia> getSocialMedia(Long pageId) {
        return socialMediaRepository.findAll();
    }

    @Override
    public SocialMedia getSocialMediaById(Long pageId, Long socialMediaId) {
        return socialMediaRepository.findById(socialMediaId).orElseThrow(() -> new ResourceNotFoundException("Social Media not found"));
    }
}
