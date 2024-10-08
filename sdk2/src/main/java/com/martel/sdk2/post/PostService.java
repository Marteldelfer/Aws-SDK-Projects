package com.martel.sdk2.post;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
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
                .key(key)
                .url(urlBase + key)
                .sender("martel")
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

    public void deleteObject(String key) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .build();
        s3Client.deleteObject(request);
    }

    public void delete(String id) {

        Post post = repository.findById(id).get();
        String key = post.getKey();

        deleteObject(key);
        repository.deleteById(id);
    }

    public Post edit(
        String id,
        MultipartFile image

    ) {
        Post post = repository.findById(id).get();
        String key = post.getKey();
        
        //first, delte s3 object
        deleteObject(key);
        //then, replace it
        try {
            RequestBody request = RequestBody.fromInputStream(
                image.getInputStream(),
                image.getSize()
            );
            save(request, key);
            return post; 
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
