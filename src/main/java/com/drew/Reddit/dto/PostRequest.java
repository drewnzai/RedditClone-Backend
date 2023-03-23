package com.drew.Reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *   Provides a way for the backend to receive a request to create a post.
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private Long postId;
    private String subredditName;
    private String postName;
    private String url;
    private String description;
}
