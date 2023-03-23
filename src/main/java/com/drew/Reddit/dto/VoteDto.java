package com.drew.Reddit.dto;


import com.drew.Reddit.models.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *   Provides a way for frontend application to access relevant Vote information.
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
    private VoteType voteType;
    private Long postId;
}
