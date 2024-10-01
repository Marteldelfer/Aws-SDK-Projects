package com.martel.sdk1.s3;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;


    @GetMapping("/{key}")
    public ResponseEntity<String> getObject(
        @PathVariable("key") String key
    ) {
        return ResponseEntity.ok(s3Service.getObject(key));
    }
    @GetMapping("/upload")
    public String uploadPage() {
        return "upload.html";
    }
    @PostMapping("/upload")
    public ResponseEntity<String> postUpload(@RequestBody String data) {
        return ResponseEntity.ok(s3Service.save(data));
    }
    
}