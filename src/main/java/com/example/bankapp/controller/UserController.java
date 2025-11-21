package com.example.bankapp.controller;

import com.example.bankapp.model.User;
import com.example.bankapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, 
                              @RequestParam String password,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName) {
        
        // Check if username already exists
        if (userRepository.findByUsername(username) != null) {
            return "redirect:/register?error=username_exists";
        }
        
        // Provide default values for missing fields
        if (email == null || email.trim().isEmpty()) {
            email = username + "@example.com";
        }
        if (firstName == null) firstName = "";
        if (lastName == null) lastName = "";
        
        try {
            // Encode password and save user
            String encodedPassword = passwordEncoder.encode(password);
            User user = new User(username, encodedPassword, email, firstName, lastName);
            userRepository.save(user);
            
            System.out.println("User registered successfully: " + username);
            return "redirect:/login?registered=true";
        } catch (Exception e) {
            System.out.println("Registration error: " + e.getMessage());
            return "redirect:/register?error=registration_failed";
        }
    }
}