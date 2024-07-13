package com.example.RentalLocation.mapper;

import com.example.RentalLocation.model.RentalLocation;
import com.example.RentalLocation.model.RentalLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalLocationMapperImp implements RentalLocationMapper{


    @Override
    public RentalLocationDTO RENTALLOCATION_toDTO(RentalLocation rentalLocation) {
        RentalLocationDTO rentalLocationDTO = new RentalLocationDTO();
        rentalLocationDTO.setId(rentalLocation.getId());
        rentalLocationDTO.setName(rentalLocation.getName());
        rentalLocationDTO.setAddress(rentalLocation.getAddress());
        rentalLocationDTO.setManager(rentalLocation.getManager());
        return rentalLocationDTO;
    }

    @Override
    public RentalLocation RENTALLOCATION_toEntity(RentalLocationDTO rentalLocationDTO) {
        RentalLocation rentalLocation = new RentalLocation();
        rentalLocation.setId(rentalLocationDTO.getId());
        rentalLocation.setName(rentalLocationDTO.getName());
     //   rentalLocation.setVehicleID(rentalLocation.getVehicleID());
        return rentalLocation;
    }
}
