package com.martel.sdk1.s3;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class S3Service {
    
    private final S3Client s3Client;
    private String bucket = "martelsdkbucket";

    public S3Service() {
        s3Client = DependencyFactory.s3Client();
    }

    public void createBucket() {
        try {
            s3Client.createBucket(
                CreateBucketRequest.builder()
                    .bucket(bucket)
                    .build()
            );
            s3Client.waiter().waitUntilBucketExists(
                HeadBucketRequest.builder()
                    .bucket(bucket)
                    .build()
            );
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }

    public String save(String data) {
        String key = "Key" + Long.toString(System.currentTimeMillis());
        s3Client.putObject(PutObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .build(),
            RequestBody.fromString(data));
        return data;
    }

    public void delete(String key) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .build();
        s3Client.deleteObject(deleteObjectRequest);
    }

    public String edit(String key, String data) {
        s3Client.putObject(PutObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .build(),
            RequestBody.fromString(data));
        return data;
    }

    public String getObject(String key) {
        GetObjectRequest request = GetObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .build();
        return s3Client.getObject(request).toString();
    }
}
