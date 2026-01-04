package com.capstone.user.service;

import com.capstone.user.dto.UserLoginRequestDTO;
import com.capstone.user.dto.UserResponseDTO;
import com.capstone.user.dto.UserSignupRequestDTO;
import com.capstone.user.entity.User;
import com.capstone.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponseDTO signup(UserSignupRequestDTO dto) {

        repository.findByEmail(dto.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("Email already exists");
                });

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // plain text for now
        user.setRole(dto.getRole());
        user.setActive(true);

        return mapToResponse(repository.save(user));
    }

    public UserResponseDTO login(UserLoginRequestDTO dto) {

        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return mapToResponse(user);
    }

    public UserResponseDTO getUserById(UUID userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToResponse(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private UserResponseDTO mapToResponse(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setActive(user.getActive());
        return dto;
    }
}
