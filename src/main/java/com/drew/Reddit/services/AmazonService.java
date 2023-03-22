package com.drew.Reddit.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AmazonService {

    private final AmazonS3 amazonS3;
    @Async
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



    public byte[] download(String path, String key) {
        try {
            S3Object object = amazonS3.getObject(path, key);
            return IOUtils.toByteArray(object.getObjectContent());

        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download file from s3", e);
        }
    }


}
