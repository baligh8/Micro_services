package com.example.Vehicle.repository;

import com.example.Vehicle.model.Vehicle;
import com.example.Vehicle.model.VehicleDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByRentalLocationID(String rentalLocationID);
}