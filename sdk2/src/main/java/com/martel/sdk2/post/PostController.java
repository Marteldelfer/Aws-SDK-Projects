package com.martel.sdk2.post;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @PostMapping("/")
    public ResponseEntity<Post> upload(
        @RequestBody MultipartFile image
    ) { 
        return service.upload(image);
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(service.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(
        @PathVariable("id") String id
    ) {
        return ResponseEntity.ok(service.getPost(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable("id") String id
    ) {
        service.delete(id);
        return ResponseEntity.accepted().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Post> edit(
        @PathVariable("id") String id,
        @RequestBody MultipartFile image
    ) {
        return ResponseEntity.ok(service.edit(id, image));
    }
}
