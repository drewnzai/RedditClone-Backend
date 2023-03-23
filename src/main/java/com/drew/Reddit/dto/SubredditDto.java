package com.drew.Reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *   Provides a way for frontend application to access relevant Subreddit information.
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubredditDto {
    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
