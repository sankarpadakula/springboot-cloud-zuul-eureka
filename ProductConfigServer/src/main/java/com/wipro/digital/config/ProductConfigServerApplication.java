package com.wipro.digital.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ProductConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductConfigServerApplication.class, args);
	}

}
