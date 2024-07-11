package com.example.RentalLocation.fiegnClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Vehicle-SERVICE", url = "http://localhost:8081/app/vehicle")
public interface VehicleClient {
    @GetMapping("/{id}")
    VehicleDTO retriveVehicleById(@PathVariable Long id);


}
