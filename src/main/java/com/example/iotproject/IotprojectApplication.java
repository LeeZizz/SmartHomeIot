package com.example.iotproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.example.iotproject.dao")
//@EntityScan(basePackages = "com.example.iotproject.model")
public class IotprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotprojectApplication.class, args);
	}

}
