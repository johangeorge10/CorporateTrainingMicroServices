package com.capstone.coursemanagement.dto;

import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String username;
    private String email;
    private String role;

    //For fall back method
    private String message;
    public UserDTO(String message) {
    	this.message=message;
    }
    public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
