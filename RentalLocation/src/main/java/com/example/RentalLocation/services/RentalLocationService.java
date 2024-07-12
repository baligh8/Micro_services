package com.example.RentalLocation.services;

import com.example.RentalLocation.model.VehicleDTO;
import com.example.RentalLocation.mapper.RentalLocationMapper;
import com.example.RentalLocation.model.RentalLocation;
import com.example.RentalLocation.model.RentalLocationDTO;
import com.example.RentalLocation.repository.RentalLocationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RentalLocationService {


    @Autowired
    private RentalLocationMapper rentalLocationMapper;
    private final RestTemplate restTemplate;
    @Autowired
    private RentalLocationRepository rentalLocationRepository;


    public RentalLocationDTO createRentalLocation(RentalLocation rentalLocation) {
        RentalLocation rentalLocation1 =  rentalLocationRepository.save(rentalLocation);
        return rentalLocationMapper.RENTALLOCATION_toDTO(rentalLocation1);
      }

    public List<RentalLocationDTO> getAllRentalLocations() {
        List<RentalLocation> rentalLocations = rentalLocationRepository.findAll();
        return rentalLocations.stream().map(rentalLocation -> {
            var vehicles = getVehicleById(rentalLocation.getId());
            var rentalLocationDTO = rentalLocationMapper.RENTALLOCATION_toDTO(rentalLocation);
            return  new RentalLocationDTO(rentalLocationDTO.getId(), rentalLocation.getName(),rentalLocation.getAddress(),rentalLocation.getManager(),vehicles);
        }).collect(Collectors.toList());
    }

    public RentalLocationDTO getDetaileLocationById(String id) {
        RentalLocation rentalLocation = rentalLocationRepository.findById(id).orElse(null);
        if (rentalLocation != null) {
            var vehicles = getVehicleById(rentalLocation.getId());
            var rentalLocationDTO = rentalLocationMapper.RENTALLOCATION_toDTO(rentalLocation);
            return  new RentalLocationDTO(rentalLocationDTO.getId(), rentalLocation.getName(),rentalLocation.getAddress(),rentalLocation.getManager(),vehicles);
        }
        return null;
    }


    public void deleteRentalLocation(String id) {
        rentalLocationRepository.deleteById(id);

    }
    public List<VehicleDTO> getVehicleById(String locationID) {
        String url = "http://localhost:8081/app/vehicle/retrieveVehicle/" + locationID;
        ResponseEntity<VehicleDTO[]> responseEntity = restTemplate.getForEntity(url, VehicleDTO[].class);
        VehicleDTO[] vehicleDTO = responseEntity.getBody();
        if (vehicleDTO == null) {
            throw new IllegalStateException("Failed to fetch Vehicle from the external service");
        }
        return Arrays.asList(vehicleDTO);
    }









}

