package com.example.backend.repository;

<<<<<<< HEAD
import com.example.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
=======
import com.example.backend.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
>>>>>>> d2ffc8ee0102455002d1c0fa7217f1e7aca70c7c
