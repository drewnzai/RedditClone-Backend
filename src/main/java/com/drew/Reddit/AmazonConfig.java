package com.drew.Reddit;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
*   Handles the creation and maintenance of Amazon resources.
* */

@Configuration
public class AmazonConfig {
    @Value(value = "${amazon.aws.access-key}")
    private String accessKey;

    @Value(value = "${amazon.aws.secret-key}")
    private String secretKey;
    @Bean
    public AmazonS3 amazons3(){

        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonS3ClientBuilder.standard()
                .withCredentials( new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.EU_NORTH_1)
                .build();
    }
}
