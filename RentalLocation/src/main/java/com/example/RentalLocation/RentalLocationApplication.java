package com.example.RentalLocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RentalLocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalLocationApplication.class, args);
	}

}
