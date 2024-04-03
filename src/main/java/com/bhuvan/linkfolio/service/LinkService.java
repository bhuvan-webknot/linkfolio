package com.bhuvan.linkfolio.service;

import com.bhuvan.linkfolio.dto.LinkRequestDto;


public interface LinkService {
    void addLink(Long pageId, LinkRequestDto linkRequestDto);
}
