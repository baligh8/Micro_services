package com.example.Vehicle.services;


import com.example.Vehicle.feignClient.RentalLocationClient;
import com.example.Vehicle.model.RentalLocationDTO;
import com.example.Vehicle.model.VehicleDTO;
import com.example.Vehicle.repository.VehicleRepository;
import com.example.Vehicle.mapper.VehicleMapper;
import com.example.Vehicle.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
@Slf4j
public class VehicleService {
    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private RentalLocationClient rentalLocationClient;
    @Autowired
    private final KafkaTemplate<String, List<Vehicle>> kafkaTemplate;
    private static final String TOPIC = "vehicle_topic";


    public VehicleDTO createVehicle(Vehicle vehicle) {
        vehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.VEHICLE_toDTO(vehicle);
    }

    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll().stream().map(vehicle -> getVehicleById(vehicle.getId())).collect(Collectors.toList());
    }


    public VehicleDTO getVehicleById(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            VehicleDTO vehicleDTO = vehicleMapper.VEHICLE_toDTO(vehicle);
            var location = rentalLocationClient.getDetaileLocationById(vehicle.getRentalLocationID());
            return new VehicleDTO(vehicleDTO.getId(), vehicleDTO.getMake(), vehicleDTO.getModel(), vehicleDTO.getLicensePlate(), location);
        } else {
            throw new IllegalArgumentException("Vehicle not found");
        }
    }

    public List<VehicleDTO> getVehicleByRentalLocationID(String rentalLocationID) {
        List<Vehicle> vehicles = vehicleRepository.findByRentalLocationID(rentalLocationID);
        return vehicles.stream().map(vehicleMapper::VEHICLE_toDTO).collect(Collectors.toList());
    }

    public VehicleDTO updateVehicle(Long id, Vehicle vehicle) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle1 = optionalVehicle.get();
            vehicle1.setMake(vehicle.getMake());
            vehicle1.setLicensePlate(vehicle.getLicensePlate());
            vehicle1.setRentalLocationID(vehicle.getRentalLocationID());
            vehicleRepository.save(vehicle1);

            RentalLocationDTO rentalLocationDTO = rentalLocationClient.getDetaileLocationById(vehicle1.getRentalLocationID());
            return new VehicleDTO(vehicle1.getId(), vehicle1.getMake(), vehicle1.getModel(), vehicle1.getLicensePlate(), rentalLocationDTO);
        } else {
            throw new IllegalArgumentException("Vehicle not found");
        }
    }


    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }


    //kafka
    private void sendvehicles(List<Vehicle> vehicles) {
        kafkaTemplate.send(TOPIC, vehicles);
    }

    public List<VehicleDTO> getAllVehicleskafka() {
        return vehicleRepository.findAll().stream().map(vehicle -> {
            var vehicle1 = getVehicleById(vehicle.getId());
            sendvehicles(Collections.singletonList(vehicle));
            return new VehicleDTO(vehicle1.getId(), vehicle1.getMake(), vehicle1.getModel(), vehicle1.getLicensePlate(), vehicle1.getRentalLocation());
        }).collect(Collectors.toList());
    }


}


