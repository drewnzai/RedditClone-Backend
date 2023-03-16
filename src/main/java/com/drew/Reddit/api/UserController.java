package com.drew.Reddit.api;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.drew.Reddit.dto.UserDto;
import com.drew.Reddit.services.AmazonService;
import com.drew.Reddit.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping (value = "{username}",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> uploadProfilePicture
            (@PathVariable(name = "username") String username,
             @RequestParam(name = "profileImage")MultipartFile multipartFile){
        userService.updateProfileImage(username, multipartFile.getOriginalFilename());
        return null;
    }
}
