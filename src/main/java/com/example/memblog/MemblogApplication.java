package com.example.memblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MemblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemblogApplication.class, args);

	}

}
