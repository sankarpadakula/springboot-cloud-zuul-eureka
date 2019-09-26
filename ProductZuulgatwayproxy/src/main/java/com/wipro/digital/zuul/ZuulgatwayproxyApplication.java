package com.wipro.digital.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.wipro.digital.zuul.filters.ErrorFilter;
import com.wipro.digital.zuul.filters.PostFilter;
import com.wipro.digital.zuul.filters.PreFilter;
import com.wipro.digital.zuul.filters.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
public class ZuulgatwayproxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulgatwayproxyApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
}
