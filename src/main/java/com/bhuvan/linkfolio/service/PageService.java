package com.bhuvan.linkfolio.service;

import com.bhuvan.linkfolio.dto.PageRequestDto;
import com.bhuvan.linkfolio.dto.UserRequestDto;
import com.bhuvan.linkfolio.dto.UserResponseDto;
import com.bhuvan.linkfolio.model.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageService {
    List<Page> getAllPages();

    Page createPage(PageRequestDto page);

    Page getPageById(Long id);
}
