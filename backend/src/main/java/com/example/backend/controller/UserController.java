package com.example.backend.controller;

<<<<<<< HEAD
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
=======
import com.example.backend.model.Patient;
import com.example.backend.repository.PatientRepository;
>>>>>>> d2ffc8ee0102455002d1c0fa7217f1e7aca70c7c
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
@RestController
@RequestMapping("/api/users")
=======
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
>>>>>>> d2ffc8ee0102455002d1c0fa7217f1e7aca70c7c
public class UserController {

    @Autowired
    private UserRepository userRepository;

<<<<<<< HEAD
    // Signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already registered.");
        }
        return ResponseEntity.ok(userRepository.save(user));
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        User user = userRepository.findByEmail(email.toLowerCase());
        if (user != null && user.getPassword().equals(password)) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials.");
        }
    }
}
=======
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("role", user.getRole());
        response.put("userId", user.getId());

        return ResponseEntity.ok(response);
    }
}
>>>>>>> d2ffc8ee0102455002d1c0fa7217f1e7aca70c7c
