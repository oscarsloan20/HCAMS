package com.example.backend.repository;

import com.example.backend.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<Patient, String> {
    // Add custom query methods in here
}
