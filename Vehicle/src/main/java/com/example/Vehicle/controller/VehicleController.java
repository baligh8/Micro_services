package com.example.Vehicle.controller;

import com.example.Vehicle.model.Vehicle;
import com.example.Vehicle.model.VehicleDTO;
import com.example.Vehicle.repository.VehicleRepository;
import com.example.Vehicle.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @DeleteMapping("/delete-vehicle/{id}")
    public void deleteArticle(@PathVariable("id") Long id) {

        vehicleService.deleteVehicle(id);
    }

    @PutMapping("/update-vehicle")
    public VehicleDTO updateArticle(@RequestBody VehicleDTO VehicleDTO) {

        return vehicleService.updateVehicle(VehicleDTO);
    }
    public List<Vehicle> getListOfVehicle(List<Long> listIDV) {
        List<Vehicle> listOfVehicles = new ArrayList<>();
        for (Long id : listIDV) {
            Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
            if (optionalVehicle.isPresent()) {
                listOfVehicles.add(optionalVehicle.get());
            }
        }
        return listOfVehicles;
    }
   /* public List<Vehicle> getListofvucke(ListIDV<Long id>){
        List<Vehicle> LisOfvehicle = new ArrayList<Vehicle>();
        for (v in ListIDV){
            Vehicle vehicle = VehicleRepository.findById(V).get();
            LisOfvehicle.set(Vehicle);
        }
        return LisOfvehicle;
    }*/
}
