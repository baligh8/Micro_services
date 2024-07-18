package com.example.rentalLocation.mapper;

import com.example.rentalLocation.model.RentalLocation;
import com.example.rentalLocation.model.RentalLocationDTO;

public interface RentalLocationMapper {
    RentalLocationDTO RENTALLOCATION_toDTO(RentalLocation rentalLocation);
    RentalLocation RENTALLOCATION_toEntity(RentalLocationDTO rentalLocationDTO);
}
