package com.martel.sdk1.s3;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("/upload")
    public ResponseEntity<String> postUpload(@RequestBody String data) {
        return ResponseEntity.ok(s3Service.save(data));
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<Void> deleteObject(
        @PathVariable("key") String key
    ) {
        s3Service.delete(key);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{key}")
    public ResponseEntity<String> editObject(
        @PathVariable("key") String key,
        @RequestBody String data
    ) {
        return ResponseEntity.ok(s3Service.edit(key, data));
    }
    
}