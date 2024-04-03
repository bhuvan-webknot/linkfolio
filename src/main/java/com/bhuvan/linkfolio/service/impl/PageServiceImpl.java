package com.bhuvan.linkfolio.service.impl;

import com.bhuvan.linkfolio.dto.LinkRequestDto;
import com.bhuvan.linkfolio.dto.PageRequestDto;
import com.bhuvan.linkfolio.dto.SocialMediaRequestDto;
import com.bhuvan.linkfolio.exceptions.ResourceNotFoundException;
import com.bhuvan.linkfolio.model.Page;
import com.bhuvan.linkfolio.model.User;
import com.bhuvan.linkfolio.repository.PageRepository;
import com.bhuvan.linkfolio.repository.UserRepository;
import com.bhuvan.linkfolio.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PageServiceImpl implements PageService{

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SocialMediaImpl socialMediaService;

    @Autowired
    private LinkServiceImpl linkService;

    @Override
    public List<Page> getAllPages() {
        return pageRepository.findAll();
    }

    @Override
    public Page createPage(PageRequestDto pageRequest) {
        Optional<User> user = userRepository.findById(pageRequest.getUserId());

        if(user.isEmpty())
            throw new ResourceNotFoundException("User not found");
        Page newPage = Page
                .builder()
                .bio(pageRequest.getBio())
                .displayName(pageRequest.getDisplayName())
                .backgroundType(pageRequest.getBackgroundType())
                .backgroundColor(pageRequest.getBackgroundColor())
                .backgroundImageUrl(pageRequest.getBackgroundImageUrl())
                .user(user.get())
                .build();

        pageRepository.save(newPage);


        List<SocialMediaRequestDto> socialMediaRequestDtos = pageRequest.getButtons();
        for(SocialMediaRequestDto socialMediaRequestDto : socialMediaRequestDtos){
            socialMediaService.addSocialMedia(newPage.getId(), socialMediaRequestDto);
        }

        for(LinkRequestDto linkRequestDto : pageRequest.getLinks()){
            linkService.addLink(newPage.getId(), linkRequestDto);
        }
        return newPage;
    }

    @Override
    public Page getPageById(Long id) {
        return pageRepository.findById(id).orElse(null);
    }
}
