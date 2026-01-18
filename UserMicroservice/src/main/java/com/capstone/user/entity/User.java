package com.capstone.user.entity;
import com.capstone.user.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(
    name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
    }
)
public class User {

	@Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @NotBlank
    @Size(min=3,max=20)
    @Pattern(regexp="^[a-zA-Z]+$")
    private String name;

    @Column(nullable = false, unique = true)
    @Email
    @Pattern(regexp="^[\\w.]+@tcs\\.com$")
    private String email;

    //@Column(nullable = false)
    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;   // ADMIN, TRAINER, TRAINEE

    @Column(nullable = false)
    private Boolean active = true;
    

	public void setRole(Role role) {
		this.role = role;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public Role getRole() {
		return role;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

    // getters & setters
    
}
