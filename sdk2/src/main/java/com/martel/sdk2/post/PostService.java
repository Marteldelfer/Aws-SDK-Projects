package com.martel.sdk2.post;

import java.util.Date;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class PostService {

    private final S3Client s3Client;
    private String bucket = "martel-sdk-posts";

    public PostService() {
        s3Client = DependencyFactory.s3Client();
    }

    public String save(RequestBody image) {
        PutObjectRequest request = PutObjectRequest.builder()
            .acl(ObjectCannedACL.PUBLIC_READ)
            .bucket(bucket)
            .key(new Date().toString())
            .contentType("image/jpeg")
            .build();
        s3Client.putObject(request, image);
        return "";
    }
    
}
