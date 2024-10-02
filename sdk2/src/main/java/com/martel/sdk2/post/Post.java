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
    String id;
    String url;
    String sender;
    Date createdAt;

}
