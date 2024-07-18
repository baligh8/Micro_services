package com.example.rentalLocation.repository;

import com.example.rentalLocation.model.RentalLocation;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface RentalLocationRepository extends MongoRepository<RentalLocation, String> {

}
