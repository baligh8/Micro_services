package com.example.RentalLocation.model;

import com.example.RentalLocation.fiegnClient.VehicleDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentalLocationDTO {

    private Long id;
    private String name;
    private String address;
    private String manager;
    private VehicleDTO vehicle;
   // private List<VehicleDTO> vehicle;

}
