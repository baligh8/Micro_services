package com.example.RentalLocation.services;

import com.example.RentalLocation.fiegnClient.VehicleClient;
import com.example.RentalLocation.mapper.RentalLocationMapper;
import com.example.RentalLocation.model.RentalLocation;
import com.example.RentalLocation.model.RentalLocationDTO;
import com.example.RentalLocation.repository.RentalLocationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
@Slf4j
public class RentalLocationService {
    @Autowired
    private VehicleClient vehicleClient;
    @Autowired
    private RentalLocationMapper rentalLocationMapper;
    @Autowired
    private RentalLocationRepository rentalLocationRepository;


    public RentalLocationDTO createRentalLocation(RentalLocation rentalLocation) {
        var rentalLocationV = rentalLocationRepository.save(rentalLocation);
        var rentalLocationDTO = rentalLocationMapper.RENTALLOCATION_toDTO(rentalLocationV);
        var vehicleDTO =  vehicleClient.retriveVehicleById(rentalLocationV.getVehicleID());
        return new RentalLocationDTO(rentalLocationDTO.getId(),rentalLocationDTO.getName(),rentalLocationDTO.getAddress(),rentalLocationDTO.getManager(),vehicleDTO);
    }

    public List<RentalLocationDTO> getAllRentalLocations() {
        List<RentalLocation> rentalLocations = rentalLocationRepository.findAll();
        return rentalLocations.stream()
                .map(rentalLocationMapper::RENTALLOCATION_toDTO)
                .collect(Collectors.toList());
    }

    public RentalLocationDTO getDetaileLocationById(Long id){
        RentalLocation rentalLocation = rentalLocationRepository.findById(id).orElse(null);
        if(rentalLocation != null) {
            return rentalLocationMapper.RENTALLOCATION_toDTO(rentalLocation);
        }
        return null;   
    }




    public RentalLocationDTO getRentalLocationById(Long id) {
        RentalLocation rentalLocation = rentalLocationRepository.findById(id).orElse(null);
        if (rentalLocation != null) {
            var rentalLocationDTO = rentalLocationMapper.RENTALLOCATION_toDTO(rentalLocation);
            var vehicleDTO =  vehicleClient.retriveVehicleById(rentalLocation.getVehicleID());
            return new RentalLocationDTO(rentalLocationDTO.getId(),rentalLocationDTO.getName(),rentalLocationDTO.getAddress(),rentalLocationDTO.getManager(),vehicleDTO);

             //  return rentalLocationMapper.RENTALLOCATION_toDTO(rentalLocation); 
        }
        return null;
    }

    public RentalLocationDTO updateRentalLocation(Long id,RentalLocationDTO rentalLocationDTO) {
        RentalLocation rentalLocationToUpdate = rentalLocationMapper.RENTALLOCATION_toEntity(rentalLocationDTO);
        rentalLocationToUpdate = rentalLocationRepository.save(rentalLocationToUpdate);
        return rentalLocationMapper.RENTALLOCATION_toDTO(rentalLocationToUpdate);
    }
    /*public RentalLocationDTO updateRentalLocation(Long id, RentalLocationDTO rentalLocationDTO) {
        RentalLocation rentalLocationToUpdate = rentalLocationMapper.RENTALLOCATION_toEntity(rentalLocationDTO);
        rentalLocationToUpdate.setId(id);
        rentalLocationToUpdate = rentalLocationRepository.save(rentalLocationToUpdate);
        return rentalLocationMapper.RENTALLOCATION_toDTO(rentalLocationToUpdate);
    }*/

    public void deleteRentalLocation(Long id) {
        rentalLocationRepository.deleteById(id);
    }

}


