package com.example.Vehicle.feignClient;

import com.example.Vehicle.model.RentalLocationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "RENTALLOCATION-MS")
public interface RentalLocationClient {
    @GetMapping("/app/rental-locations/detaile/{id}")
     RentalLocationDTO getDetaileLocationById(@PathVariable String id) ;
}
