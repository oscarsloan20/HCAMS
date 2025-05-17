package com.example.backend.service;

import com.example.backend.model.Appointment;
import com.example.backend.model.Doctor;
import com.example.backend.model.Patient;
import com.example.backend.repository.AppointmentRepository;
import com.example.backend.repository.DoctorRepository;
import com.example.backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    // Book a new appointment
    public Appointment bookAppointment(Appointment appointment) {
        String doctorId = appointment.getDoctor().getId();
        String patientId = appointment.getPatient().getId();
        String date = appointment.getDate();
        String time = appointment.getTime();

        // Validate doctor
        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        if (doctorOpt.isEmpty()) {
            throw new IllegalArgumentException("Doctor not found.");
        }

        // Validate patient
        Optional<Patient> patientOpt = patientRepository.findById(patientId);
        if (patientOpt.isEmpty()) {
            throw new IllegalArgumentException("Patient not found.");
        }

        // Check for appointment conflict
        boolean exists = appointmentRepository.existsByDoctorIdAndDateAndTime(doctorId, date, time);
        if (exists) {
            throw new IllegalArgumentException("This time slot is already booked.");
        }

        appointment.setDoctor(doctorOpt.get());
        appointment.setPatient(patientOpt.get());
        appointment.setStatus("Confirmed");

        return appointmentRepository.save(appointment);
    }

    // Retrieve all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Get appointments by doctor ID
    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    // Get appointments by patient ID
    public List<Appointment> getAppointmentsByPatient(String patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    // Update appointment status
    public Appointment updateStatus(String id, String newStatus) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            appointment.setStatus(newStatus);
            return appointmentRepository.save(appointment);
        }
        return null;
    }

    // Delete an appointment
    public boolean deleteAppointment(String id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Cancel an appointment (sets status to "Cancelled")
    public Appointment cancelAppointment(String appointmentId) {
        Optional<Appointment> optional = appointmentRepository.findById(appointmentId);
        if (optional.isPresent()) {
            Appointment appointment = optional.get();
            appointment.setStatus("Cancelled");
            return appointmentRepository.save(appointment);
        }
        return null;
    }

    // Reschedule an appointment (change date and time)
    public Appointment rescheduleAppointment(String appointmentId, String newDate, String newTime) {
        Optional<Appointment> optional = appointmentRepository.findById(appointmentId);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Appointment not found.");
        }

        Appointment appointment = optional.get();
        String doctorId = appointment.getDoctor().getId();

        // Conflict check for new time slot
        boolean conflict = appointmentRepository.existsByDoctorIdAndDateAndTime(doctorId, newDate, newTime);
        if (conflict) {
            throw new IllegalArgumentException("Doctor is not available at the new time.");
        }

        appointment.setDate(newDate);
        appointment.setTime(newTime);
        appointment.setStatus("Rescheduled");

        return appointmentRepository.save(appointment);
    }
}