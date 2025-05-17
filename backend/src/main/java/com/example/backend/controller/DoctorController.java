package com.example.backend.controller;

import com.example.backend.model.Doctor;
import com.example.backend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    // Create a new doctor
    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Get all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable String id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        return doctor.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    // Update doctor info
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable String id, @RequestBody Doctor updatedDoctor) {
        return doctorRepository.findById(id).map(doctor -> {
            doctor.setName(updatedDoctor.getName());
            doctor.setSpecialisation(updatedDoctor.getSpecialisation());
            doctor.setSchedule(updatedDoctor.getSchedule());
            return ResponseEntity.ok(doctorRepository.save(doctor));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable String id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}