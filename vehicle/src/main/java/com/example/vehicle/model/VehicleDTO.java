package com.example.vehicle.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleDTO {

    private Long id;
    private String make;
    private String model;
    private String licensePlate;
    private RentalLocationDTO rentalLocation;


}
