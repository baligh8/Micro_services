package com.example.rentalLocation.services;

import com.example.rentalLocation.model.VehicleDTO;
import com.example.rentalLocation.mapper.RentalLocationMapper;
import com.example.rentalLocation.model.RentalLocation;
import com.example.rentalLocation.model.RentalLocationDTO;
import com.example.rentalLocation.repository.RentalLocationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    private final List<VehicleDTO> receivedVehicle = new ArrayList<>();



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
        Optional<RentalLocation> optionalRentalLocation = rentalLocationRepository.findById(id);

        if (optionalRentalLocation.isPresent()) {
            RentalLocation rentalLocation = optionalRentalLocation.get();
            var rentalLocationDto =  rentalLocationMapper.RENTALLOCATION_toDTO(rentalLocation);
            return rentalLocationDto;
        } else {
            throw new IllegalArgumentException("RentalLocation not found with id: " + id);
        }
    }


    public void deleteRentalLocation(String id) {
        rentalLocationRepository.deleteById(id);

    }
    public List<VehicleDTO> getVehicleById(String locationID) {
        String url = "http://vehicle:8081/vehicle/retrieveVehicle/" + locationID;
        ResponseEntity<VehicleDTO[]> responseEntity = restTemplate.getForEntity(url, VehicleDTO[].class);
        VehicleDTO[] vehicleDTO = responseEntity.getBody();
        if (vehicleDTO == null) {
            throw new IllegalStateException("Failed to fetch Vehicle from the external service");
        }
        return Arrays.asList(vehicleDTO);
    }


    //kafka
    @KafkaListener(topics = "vehicle_topic", groupId = "rentalLocation-group")
    public void listen(List<VehicleDTO> vehicle) {
        System.out.println("Received vehicle: " + vehicle);
        receivedVehicle.addAll(vehicle);
    }

    public List<VehicleDTO> receivedVehicle() {
        return new ArrayList<>(receivedVehicle);
    }









}

