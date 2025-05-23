package com.example.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "doctors")
public class Doctor {

    @Id
    private String id;

    private String name;
    private String specialisation;

    // Example: {"Monday": ["09:00", "10:00"], "Wednesday": ["14:00"]}
    private Map<String, List<String>> schedule;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialisation() { return specialisation; }
    public void setSpecialisation(String specialisation) { this.specialisation = specialisation; }

    public Map<String, List<String>> getSchedule() { return schedule; }
    public void setSchedule(Map<String, List<String>> schedule) { this.schedule = schedule; }
}