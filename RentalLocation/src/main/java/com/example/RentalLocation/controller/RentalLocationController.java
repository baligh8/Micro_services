package com.example.RentalLocation.controller;

import com.example.RentalLocation.model.RentalLocation;
import com.example.RentalLocation.model.RentalLocationDTO;
import com.example.RentalLocation.services.RentalLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental-locations")
public class RentalLocationController {
    @Autowired
     private RentalLocationService rentalLocationService;

    @GetMapping("/retrieveAllRentalLocations")
    public List<RentalLocationDTO> getAllRentalLocations() {
        return rentalLocationService.getAllRentalLocations();
    }

    @GetMapping("/retrieve-rentalLocation/{id}")
    public RentalLocationDTO getRentalLocationById(@PathVariable Long id) {
        return rentalLocationService.getRentalLocationById(id);
    }

    @PostMapping("/add-rentalLocation")
    public RentalLocationDTO createRentalLocation(@RequestBody RentalLocation rentalLocation) {
        return rentalLocationService.createRentalLocation(rentalLocation);
    }

    @PutMapping("/update-rentalLocation/{id}")
    public RentalLocationDTO updateRentalLocation(@PathVariable Long id, @RequestBody RentalLocationDTO updatedRentalLocationDTO) {
        return rentalLocationService.updateRentalLocation(id, updatedRentalLocationDTO);
    }

    @DeleteMapping("/delete-rentalLocation/{id}")
    public void deleteRentalLocation(@PathVariable Long id) {
        rentalLocationService.deleteRentalLocation(id);
    }
}

