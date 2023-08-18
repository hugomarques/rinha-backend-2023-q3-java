package com.hugomarques.rinhabackend2023;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class RinhaBackend2023Application {

	public static void main(String[] args) {
		SpringApplication.run(RinhaBackend2023Application.class, args);
	}

}
