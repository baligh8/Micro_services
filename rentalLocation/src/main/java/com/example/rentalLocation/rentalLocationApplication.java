package com.example.rentalLocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class rentalLocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(rentalLocationApplication.class, args);
	}

}
