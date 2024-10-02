package com.martel.sdk2.post;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Document
public class Post {
    
    @Id
    private String id;
    private String key;
    private String url;
    private String sender;
    private Date createdAt;

}
