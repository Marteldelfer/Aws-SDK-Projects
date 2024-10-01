package com.martel.sdk1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.martel.sdk1.s3.S3Service;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class Sdk1Application {
	
	private final S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(Sdk1Application.class, args);

		
	}

	@Bean
	public CommandLineRunner createBucket() {
		return args -> {
			s3Service.createBucket();
			s3Service.save("First Test Object");
		};
	}

}
