package com.martel.sdk2.post;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    private final S3Client s3Client = DependencyFactory.s3Client();
    private final String urlBase = "https://martel-sdk-posts.s3.sa-east-1.amazonaws.com/";
    private String bucket = "martel-sdk-posts";

    public void save(RequestBody image, String key) {
        PutObjectRequest request = PutObjectRequest.builder()
            .acl(ObjectCannedACL.PUBLIC_READ)
            .bucket(bucket)
            .key(key)
            .contentType("image/jpeg")
            .build();
        s3Client.putObject(request, image);
    }

    public ResponseEntity<Post> upload(MultipartFile image) {
        
        try {
            RequestBody request = RequestBody.fromInputStream(
                image.getInputStream(),
                image.getSize()
            );
            String key = Long.toString(System.currentTimeMillis());

            save(request, key);

            //create post
            Post post = Post.builder()
                .sender("martel")
                .url(urlBase + key)
                .createdAt(new Date())
                .build();

            repository.save(post);

            return ResponseEntity.ok(post);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public Post getPost(String id) {
        return repository.findById(id).get();
    }
}
