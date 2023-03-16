package com.drew.Reddit.api;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.drew.Reddit.dto.UserDto;
import com.drew.Reddit.services.AmazonService;
import com.drew.Reddit.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final AmazonService amazonService;

    private final UserService userService;
    @GetMapping("{username}")
    public UserDto getUserDetails(@PathVariable(name = "username") String username){
        return userService.getUserDetails(username);
    }
}
