package com.hugomarques.rinhabackend2023;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class RinhaBackend2023Application {

	public static void main(String[] args) {
		SpringApplication.run(RinhaBackend2023Application.class, args);
	}

}
