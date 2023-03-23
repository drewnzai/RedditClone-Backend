package com.drew.Reddit.api;

import com.drew.Reddit.dto.UserDto;
import com.drew.Reddit.exceptions.SpringRedditException;
import com.drew.Reddit.models.User;
import com.drew.Reddit.services.AmazonService;
import com.drew.Reddit.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

/*
*   This API endpoint provides a given user's information to the public
*       while providing a way for a user to update their information.
* */

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final AmazonService amazonService;

    private final UserService userService;

    @Value(value = "${amazon.s3.bucket-name}")
    private String bucketName = "redditclone-store";

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
             @RequestParam(name = "profileImage")MultipartFile multipartFile) throws IOException {
        User user = userService.getUser(username);

        if (user == null) {

            throw new SpringRedditException("user does not exist");

        }

        Map<String, String> metadata = getMetadata(multipartFile);

       String path = String.format("%s/%s", bucketName, user.getUsername());

       String filename = String.format("%s-%s", multipartFile.getOriginalFilename(), UUID.randomUUID());

       amazonService.save(path, filename, Optional.of(metadata), multipartFile.getInputStream());

        userService.updateProfileImage(username, filename);

        return new ResponseEntity<>("Profile image uploaded successfully", OK);
    }

    private static Map<String, String> getMetadata(MultipartFile multipartFile) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", multipartFile.getContentType());
        metadata.put("Content-Length", String.valueOf(multipartFile.getSize()));
        return metadata;
    }

}
