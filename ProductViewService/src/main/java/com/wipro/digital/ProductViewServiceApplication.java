package com.wipro.digital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductViewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductViewServiceApplication.class, args);
	}

	@Bean
    @LoadBalanced
    public RestTemplate restTemplaye(RestTemplateBuilder builder){
        return builder.build();
    }
}
