package com.partha.publishresultservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class PublishResultServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublishResultServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplateInstance()
	{
		return new RestTemplate();
	}


}
