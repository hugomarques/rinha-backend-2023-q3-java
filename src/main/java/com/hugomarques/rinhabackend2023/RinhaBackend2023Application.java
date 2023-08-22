package com.hugomarques.rinhabackend2023;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class RinhaBackend2023Application {

	public static void main(String[] args) {
		SpringApplication.run(RinhaBackend2023Application.class, args);
	}

	@Bean
	public ApplicationRunner validateDataSource(DataSource dataSource) {
		return args -> {
			try (Connection connection = dataSource.getConnection()) {
			}
		};
	}

}
