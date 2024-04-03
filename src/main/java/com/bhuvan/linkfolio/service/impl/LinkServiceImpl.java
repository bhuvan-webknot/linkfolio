package com.bhuvan.linkfolio.service.impl;

import com.bhuvan.linkfolio.dto.LinkRequestDto;
import com.bhuvan.linkfolio.exceptions.ResourceNotFoundException;
import com.bhuvan.linkfolio.model.Link;
import com.bhuvan.linkfolio.model.Page;
import com.bhuvan.linkfolio.repository.LinkRepository;
import com.bhuvan.linkfolio.repository.PageRepository;
import com.bhuvan.linkfolio.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private LinkRepository linkRepository;

    @Override
    public void addLink(Long pageId, LinkRequestDto linkRequestDto) {
        Page pageForLink = pageRepository.findById(pageId).orElseThrow(() -> new ResourceNotFoundException("Page not found"));
        Link newlink = Link
                .builder()
                .name(linkRequestDto.getName())
                .link(linkRequestDto.getLink())
                .page(pageForLink)
                .build();

        linkRepository.save(newlink);
    }
}
