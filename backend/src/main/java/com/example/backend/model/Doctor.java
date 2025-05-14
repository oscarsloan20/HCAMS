package com.example.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.List;

@Document(collection = "doctors")
public class Doctor {

    @Id
    private String id;

    private String name;
    private String specialization;

    // Structure example: {"Monday": ["09:00", "10:00"], "Wednesday": ["14:00", "15:00"]}
    private Map<String, List<String>> schedule;

    // Getters/Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public Map<String, List<String>> getSchedule() { return schedule; }
    public void setSchedule(Map<String, List<String>> schedule) { this.schedule = schedule; }
}
