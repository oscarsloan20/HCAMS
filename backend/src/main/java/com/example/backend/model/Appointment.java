package com.example.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "appointments")
public class Appointment {

    @Id
    private String id;

    private String reason;
    private String status; // "Booked", "Cancelled", "Completed"
    private LocalDateTime appointmentTime;

    private Doctor doctor;   // full doctor object
    private Patient patient; // full patient object

    // === Getters and Setters ===
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(LocalDateTime appointmentTime) { this.appointmentTime = appointmentTime; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    // Compatibility with getDate() and getTime()
    public String getDate() {
        return appointmentTime != null ? appointmentTime.toLocalDate().toString() : null;
    }

    public void setDate(String date) {
        if (this.appointmentTime == null) {
            this.appointmentTime = LocalDateTime.parse(date + "T00:00:00");
        } else {
            this.appointmentTime = LocalDateTime.of(
                LocalDateTime.parse(date + "T00:00:00").toLocalDate(),
                this.appointmentTime.toLocalTime()
            );
        }
    }

    public String getTime() {
        return appointmentTime != null ? appointmentTime.toLocalTime().toString() : null;
    }

    public void setTime(String time) {
        if (this.appointmentTime == null) {
            this.appointmentTime = LocalDateTime.parse("1970-01-01T" + time);
        } else {
            this.appointmentTime = LocalDateTime.of(
                this.appointmentTime.toLocalDate(),
                LocalDateTime.parse("1970-01-01T" + time).toLocalTime()
            );
        }
    }
}