package com.bhuvan.linkfolio.controller;


import com.bhuvan.linkfolio.dto.PageRequestDto;
import com.bhuvan.linkfolio.model.Page;
import com.bhuvan.linkfolio.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pages")
public class PageController {

    @Autowired
    private PageService pageService;
    @GetMapping
    public ResponseEntity<List<Page>> getAllPages(){
        return ResponseEntity.ok(pageService.getAllPages());
    }

    @PostMapping
    public ResponseEntity<Page> createPage(@RequestBody PageRequestDto page){
        return ResponseEntity.ok(pageService.createPage(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page> getPageById(@PathVariable Long id){
        return ResponseEntity.ok(pageService.getPageById(id));
    }
}
