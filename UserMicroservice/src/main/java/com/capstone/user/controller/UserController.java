package com.capstone.user.controller;

import com.capstone.user.common.ApiResponse;
import com.capstone.user.dto.UserLoginRequestDTO;
import com.capstone.user.dto.UserResponseDTO;
import com.capstone.user.dto.UserSignupRequestDTO;
import com.capstone.user.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public UserResponseDTO signup(@RequestBody UserSignupRequestDTO dto) {
        return service.signup(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDTO>> login(
            @Valid @RequestBody UserLoginRequestDTO dto) {

        UserResponseDTO user = service.login(dto);

        ApiResponse<UserResponseDTO> response =
                new ApiResponse<>(true, "Login successful", user);

        return ResponseEntity.ok(response);
    }



    
    @GetMapping("/{userId}")
    public UserResponseDTO getUser(@PathVariable UUID userId) {
    	System.out.println("CMOSSOSOS");
        return service.getUserById(userId);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return service.getAllUsers();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable UUID userId) {

        UserResponseDTO user = service.getUserById(userId);

        // 🚫 Prevent deleting admin account
        if ("ADMIN".equalsIgnoreCase(user.getRole().toString()) ||
            "admin@capstone.com".equalsIgnoreCase(user.getEmail())) {
            throw new RuntimeException("Admin user cannot be deleted");
        }

        service.deleteUser(userId);
        return "User deleted successfully";
    }

}
