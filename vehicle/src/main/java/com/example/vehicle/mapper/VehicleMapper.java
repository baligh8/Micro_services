package com.example.vehicle.mapper;

import com.example.vehicle.model.Vehicle;
import com.example.vehicle.model.VehicleDTO;

public interface VehicleMapper {
    VehicleDTO VEHICLE_toDTO(Vehicle vehicle);
    Vehicle VEHICLE_toEntity(VehicleDTO vehicleDTO);
}
