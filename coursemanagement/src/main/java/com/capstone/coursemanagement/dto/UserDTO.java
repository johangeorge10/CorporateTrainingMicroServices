package com.capstone.coursemanagement.dto;

import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String name;        // Changed from 'username' to 'name'
    private String email;
    private String role;        // Keep as String, we'll handle the enum
    private Boolean active;
    private String token;
    
    // Default constructor
    public UserDTO() {}
    
    // Constructor for fallback
    private String message;
    public UserDTO(String message) {
        this.message = message;
        this.role = message;  // Set role to message for fallback
    }
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}