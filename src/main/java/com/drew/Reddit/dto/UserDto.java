package com.drew.Reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
*   Provides a way for frontend application to access relevant User information.
* */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private long id;

    private String username;

    private String profile_image;


}
