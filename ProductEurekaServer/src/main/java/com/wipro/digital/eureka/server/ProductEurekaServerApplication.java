package com.wipro.digital.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ProductEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductEurekaServerApplication.class, args);
	}

}
