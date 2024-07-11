package com.example.Vehicle.mapper;

import com.example.Vehicle.model.Vehicle;
import com.example.Vehicle.model.VehicleDTO;

public interface VehicleMapper {
    VehicleDTO VEHICLE_toDTO(Vehicle vehicle);
    Vehicle VEHICLE_toEntity(VehicleDTO vehicleDTO);
}
