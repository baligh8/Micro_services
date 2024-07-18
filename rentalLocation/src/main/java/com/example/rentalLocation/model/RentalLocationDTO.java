package com.example.rentalLocation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentalLocationDTO {

    private String id;
    private String name;
    private String address;
    private String manager;
    private List<VehicleDTO> vehicle;

}
