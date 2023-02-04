package com.hailing.costa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class CostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CostaApplication.class, args);
		System.out.println("Web API Service starts running.");
	}

}
