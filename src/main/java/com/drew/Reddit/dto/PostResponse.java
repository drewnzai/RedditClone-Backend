package com.drew.Reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *   Provides a way for the backend to send information to the frontend application once
 *      a post has been successfully created.
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String postName;
    private String url;
    private String description;
    private String userName;
    private String subredditName;
    private Integer voteCount;
    private String duration;
    private boolean upVote;
    private boolean downVote;
    private Integer commentCount;
}
