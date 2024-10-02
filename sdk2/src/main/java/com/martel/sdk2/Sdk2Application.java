package com.martel.sdk2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.martel.sdk2.post.PostRepository;

@SpringBootApplication
public class Sdk2Application {

	@Autowired
	PostRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(Sdk2Application.class, args);
	}
	/*
	@Bean
	public CommandLineRunner commandLineRunner() {

		return args -> {
			//testing database
			Post post = Post.builder()
				.url("www.com")
				.createdAt(new Date())
				.sender("martel")
				.build();
			repo.save(post);
		};
	}
	*/
}
