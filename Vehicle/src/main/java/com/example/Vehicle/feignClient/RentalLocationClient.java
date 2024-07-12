package com.example.Vehicle.feignClient;

import com.example.Vehicle.model.RentalLocationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RentalLocation-SERVICE", url = "http://localhost:8082/app/rental-locations")
public interface RentalLocationClient {
    @GetMapping("/detaile/{id}")
    public RentalLocationDTO getDetaileLocationById(@PathVariable Long id) ;
}
