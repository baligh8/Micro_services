package com.example.rentalLocation.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "RentalLocation")
public class RentalLocation  {
    @Id
    private String id;
    private String name;
    private String address;
    private String manager;

}
