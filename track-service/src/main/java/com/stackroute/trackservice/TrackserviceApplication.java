package com.stackroute.trackservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TrackserviceApplication
{
	public static void main(String[] args) {
		SpringApplication.run(TrackserviceApplication.class, args);
	}

}

