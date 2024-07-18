package com.example.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class vehicleApplication {

	public static void main(String[] args) {
		SpringApplication.run(vehicleApplication.class, args);
	}

}
