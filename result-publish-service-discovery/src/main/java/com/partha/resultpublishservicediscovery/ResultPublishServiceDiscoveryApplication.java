package com.partha.resultpublishservicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ResultPublishServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResultPublishServiceDiscoveryApplication.class, args);
	}

}
