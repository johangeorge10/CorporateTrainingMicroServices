package com.capstone.user.dto;

import com.capstone.user.entity.Role;
import com.capstone.user.validation.StrongPassword;

import jakarta.validation.constraints.NotBlank;

public class UserSignupRequestDTO {

    private String name;
    private String email;
    @NotBlank
    @StrongPassword
    private String password;
    private Role role; // ADMIN / EMPLOYEE
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

    // getters & setters
    
}
