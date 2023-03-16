package com.drew.Reddit.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AmazonService {

    private final AmazonS3 amazonS3;

    public void save(String path, String fileName,
                     Optional<Map<String, String>> optionalMetadata,
                     InputStream inputStream){

        ObjectMetadata objectMetadata = new ObjectMetadata();

        optionalMetadata.ifPresent(map -> {

            if(!map.isEmpty()){

                map.forEach(objectMetadata::addUserMetadata);
            }
        });

            amazonS3.putObject(path, fileName, inputStream, objectMetadata);

    }

}
