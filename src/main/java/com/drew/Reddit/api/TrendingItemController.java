package com.drew.Reddit.api;

import com.drew.Reddit.dto.TrendingItemsDto;
import com.drew.Reddit.services.TrendingItemsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* This API endpoint provides the content (images, description, and Subreddit information) to the Trending section
* */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/trending")
public class TrendingItemController {
    
    private final TrendingItemsService service;

    @PostMapping
    public ResponseEntity<TrendingItemsDto> createTrendingItem(@RequestBody TrendingItemsDto trendingItemsDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(trendingItemsDto));
    }

    @GetMapping
    public ResponseEntity<List<TrendingItemsDto>> getAllSubreddits() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAll());
    }

}


