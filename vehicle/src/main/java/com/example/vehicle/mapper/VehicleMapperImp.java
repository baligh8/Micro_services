package com.example.vehicle.mapper;


import com.example.vehicle.model.Vehicle;
import com.example.vehicle.model.VehicleDTO;

import org.springframework.stereotype.Component;

@Component
public class VehicleMapperImp implements  VehicleMapper{



    @Override
    public VehicleDTO VEHICLE_toDTO(Vehicle vehicle)
    {
        VehicleDTO vehicleDTO  = new VehicleDTO();
        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setMake(vehicle.getMake());
        return  vehicleDTO;

    }

    @Override
    public Vehicle VEHICLE_toEntity(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDTO.getId());
        vehicle.setMake(vehicleDTO.getMake());
        return vehicle;
    }





}