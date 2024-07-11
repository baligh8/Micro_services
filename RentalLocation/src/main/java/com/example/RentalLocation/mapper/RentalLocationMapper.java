package com.example.RentalLocation.mapper;

import com.example.RentalLocation.model.RentalLocation;
import com.example.RentalLocation.model.RentalLocationDTO;

public interface RentalLocationMapper {
    RentalLocationDTO RENTALLOCATION_toDTO(RentalLocation rentalLocation);
    RentalLocation RENTALLOCATION_toEntity(RentalLocationDTO rentalLocationDTO);
}
