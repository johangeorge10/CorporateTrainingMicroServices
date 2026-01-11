package com.capstone.user.service;

import com.capstone.user.dto.UserLoginRequestDTO;
import com.capstone.user.dto.UserResponseDTO;
import com.capstone.user.dto.UserSignupRequestDTO;
import com.capstone.user.entity.User;
import com.capstone.user.repository.UserRepository;
import com.capstone.user.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository repository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ---------------- SIGNUP ----------------
    public UserResponseDTO signup(UserSignupRequestDTO dto) {

        repository.findByEmail(dto.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("Email already exists");
                });

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        // 🔐 HASH PASSWORD
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user.setRole(dto.getRole());
        user.setActive(true);

        User savedUser = repository.save(user);

        return mapToResponse(savedUser, null);
    }

    // ---------------- LOGIN ----------------
    public UserResponseDTO login(UserLoginRequestDTO dto) {

        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        // 🔐 VERIFY HASHED PASSWORD
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // 🔑 GENERATE JWT
        String token = jwtUtil.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return mapToResponse(user, token);
    }

    // ---------------- GET USER ----------------
    public UserResponseDTO getUserById(UUID userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(user.getName()+"" +user.getRole());
        return mapToResponse(user, null);
    }

    // ---------------- GET ALL USERS ----------------
    public List<UserResponseDTO> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(user -> mapToResponse(user, null))
                .collect(Collectors.toList());
    }

    // ---------------- MAPPER ----------------
    private UserResponseDTO mapToResponse(User user, String token) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setActive(user.getActive());
        dto.setToken(token);   // token only on login
        return dto;
    }
}
