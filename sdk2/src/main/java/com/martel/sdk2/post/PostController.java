package com.martel.sdk2.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @PostMapping("/upload")
    public ResponseEntity<Post> upload(
        @RequestBody MultipartFile image
    ) { 
        return service.upload(image);
    }
}
