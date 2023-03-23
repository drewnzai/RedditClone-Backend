package com.drew.Reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/*
 *   Provides a way for frontend application to access relevant Comment information.
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
    private Long id;
    private Long postId;
    private Instant createdDate;
    private String text;
    private String userName;
}
