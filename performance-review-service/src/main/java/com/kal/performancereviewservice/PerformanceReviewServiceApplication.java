package com.kal.performancereviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class PerformanceReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerformanceReviewServiceApplication.class, args);
	}

}
