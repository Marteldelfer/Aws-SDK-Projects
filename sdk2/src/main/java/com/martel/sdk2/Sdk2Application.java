package com.martel.sdk2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Sdk2Application {

	public static void main(String[] args) {
		SpringApplication.run(Sdk2Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {

		return args -> {
		};
	}
}
