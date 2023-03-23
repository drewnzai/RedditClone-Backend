package com.drew.Reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *   Provides a way for frontend application to access relevant Trending item information.
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrendingItemsDto {
    private Long id;
    private String subreddit_name;
    private String subreddit_description;
    private String image_src;
    private String title;
}
