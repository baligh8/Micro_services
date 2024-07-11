package com.example.Vehicle.services;


import com.example.Vehicle.model.VehicleDTO;
import com.example.Vehicle.repository.VehicleRepository;
import com.example.Vehicle.mapper.VehicleMapper;
import com.example.Vehicle.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
@Slf4j
public class VehicleService {
    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private VehicleRepository vehicleRepository;


    public VehicleDTO createVehicle(Vehicle vehicle) {
        vehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.VEHICLE_toDTO(vehicle);
    }

    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(vehicleMapper::VEHICLE_toDTO)
                .collect(Collectors.toList());
    }



    public VehicleDTO getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .map(vehicleMapper::VEHICLE_toDTO)
                .orElse(null);
    }

    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.VEHICLE_toEntity(vehicleDTO);
        vehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.VEHICLE_toDTO(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }



}
