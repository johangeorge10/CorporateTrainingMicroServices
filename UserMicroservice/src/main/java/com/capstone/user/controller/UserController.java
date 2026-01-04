package com.capstone.user.controller;

import com.capstone.user.dto.UserLoginRequestDTO;
import com.capstone.user.dto.UserResponseDTO;
import com.capstone.user.dto.UserSignupRequestDTO;
import com.capstone.user.service.UserService;
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
    public UserResponseDTO login(@RequestBody UserLoginRequestDTO dto) {
        return service.login(dto);
    }

    @GetMapping("/{userId}")
    public UserResponseDTO getUser(@PathVariable UUID userId) {
        return service.getUserById(userId);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return service.getAllUsers();
    }
}
