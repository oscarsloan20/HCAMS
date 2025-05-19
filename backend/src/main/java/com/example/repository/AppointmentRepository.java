package com.example.backend.repository;

import com.example.backend.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {

    // Retrieve all appointments for a specific doctor
    List<Appointment> findByDoctorId(String doctorId);

    // Retrieve all appointments for a specific patient
    List<Appointment> findByPatientId(String patientId);

    // Check for scheduling conflicts
    boolean existsByDoctorIdAndDateAndTime(String doctorId, String date, String time);
}

