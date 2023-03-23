package com.drew.Reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/*
 *   Provides a way for the backend to receive relevant logout information.
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequest {
    @NotBlank
    private String refreshToken;
}
