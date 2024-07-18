package com.example.vehicle.controller;

import com.example.vehicle.model.Vehicle;
import com.example.vehicle.model.VehicleDTO;
import com.example.vehicle.repository.VehicleRepository;
import com.example.vehicle.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/add-vehicle")
    public VehicleDTO addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    @GetMapping("/retrieveAllVehicle")
    public List<VehicleDTO> getVehicle() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public VehicleDTO retrieveVehicle(@PathVariable("id")  Long id) {
        return vehicleService.getVehicleById(id);
    }
    @GetMapping("/retrieveVehicle/{id}")
    public List<VehicleDTO> retrieveVehicleByRentalLocationID(@PathVariable("id")  String id) {
        return vehicleService.getVehicleByRentalLocationID(id);
    }


    @DeleteMapping("/delete-vehicle/{id}")
    public void deleteVehicle(@PathVariable("id") Long id) {
        vehicleService.deleteVehicle(id);
    }

    @PutMapping("/{id}")
    public VehicleDTO updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(id,vehicle);
    }

    //KAFAK
    @GetMapping("/kafka-allV")
    public List<VehicleDTO> getVehiclekafka() {
        return vehicleService.getAllVehicleskafka();
    }


}
