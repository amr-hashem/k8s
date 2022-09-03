package com.example.plant.PlantIntegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PlantIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantIntegrationApplication.class, args);
	}

}
