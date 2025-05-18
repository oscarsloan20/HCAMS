package com.example.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;


@Document("users")
public class User {
    @Id
    private String id;

    private String email;
    private String password;
    private String role;
    private String patientId;
    private String doctorId;
}
