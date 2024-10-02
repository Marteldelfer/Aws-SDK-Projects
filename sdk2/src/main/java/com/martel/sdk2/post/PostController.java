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
    public ResponseEntity<String> upload(
        @RequestBody MultipartFile image
    ) { try {
            software.amazon.awssdk.core.sync.RequestBody requestBody =
            software.amazon.awssdk.core.sync.RequestBody.fromInputStream(
                image.getInputStream(), image.getSize()
                );
            service.save(requestBody);

            return ResponseEntity.ok("Saved");

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
