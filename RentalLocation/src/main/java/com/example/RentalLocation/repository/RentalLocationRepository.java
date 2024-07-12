package com.example.RentalLocation.repository;

import com.example.RentalLocation.model.RentalLocation;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface RentalLocationRepository extends MongoRepository<RentalLocation, String> {

}
