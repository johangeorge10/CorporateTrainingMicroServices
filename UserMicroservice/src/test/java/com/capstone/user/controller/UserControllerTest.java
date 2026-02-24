package com.capstone.user.controller;

import com.capstone.user.dto.UserLoginRequestDTO;
import com.capstone.user.dto.UserResponseDTO;
import com.capstone.user.dto.UserSignupRequestDTO;
import com.capstone.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

// Static imports for readable assertions
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false) // Disables Security (403/401) errors for this test
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper; // Converts DTOs to JSON strings

    // ==========================================
    // 1. TEST SIGNUP
    // ==========================================
    @Test
    void testSignup_Success() throws Exception {
        // 1. Arrange: Prepare the Request DTO
        UserSignupRequestDTO signupReq = new UserSignupRequestDTO();
        signupReq.setName("New User");
        signupReq.setEmail("new@capstone.com");
        signupReq.setPassword("StrongP@ss1");
        // Note: We don't set Role here as it depends on your Enum logic, 
        // but the Mock will return whatever we tell it to.

        // 2. Arrange: Prepare the Expected Response DTO
        UserResponseDTO mockResponse = new UserResponseDTO();
        mockResponse.setId(UUID.randomUUID());
        mockResponse.setName("New User");
        mockResponse.setEmail("new@capstone.com");
        mockResponse.setActive(true);

        // 3. Mock: When service.signup is called, return our mockResponse
        when(userService.signup(any(UserSignupRequestDTO.class))).thenReturn(mockResponse);

        // 4. Act & Assert: Call the API
        mockMvc.perform(post("/api/users/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupReq)))
                .andExpect(status().isOk())
                // Verify the response body matches our mockResponse
                .andExpect(jsonPath("$.name").value("New User"))
                .andExpect(jsonPath("$.email").value("new@capstone.com"))
                .andExpect(jsonPath("$.id").exists());
    }

    // ==========================================
    // 2. TEST LOGIN
    // ==========================================
    @Test
    void testLogin_Success() throws Exception {
        // 1. Arrange: Prepare Login Request
        UserLoginRequestDTO loginReq = new UserLoginRequestDTO();
        loginReq.setEmail("john@capstone.com");
        loginReq.setPassword("password123");

        // 2. Arrange: Prepare the User Data that comes back
        UserResponseDTO userResponse = new UserResponseDTO();
        userResponse.setId(UUID.randomUUID());
        userResponse.setName("John Doe");
        userResponse.setEmail("john@capstone.com");
        userResponse.setToken("fake-jwt-token-12345"); // Simulate the token

        // 3. Mock: When service.login is called, return the user data
        when(userService.login(any(UserLoginRequestDTO.class))).thenReturn(userResponse);

        // 4. Act & Assert: Call the API
        mockMvc.perform(post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginReq)))
                .andExpect(status().isOk())
                
                // IMPORTANT: Verify the 'ApiResponse' wrapper structure
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Login successful"))
                
                // Verify the nested 'data' object contains the user info
                .andExpect(jsonPath("$.data.email").value("john@capstone.com"))
                .andExpect(jsonPath("$.data.token").value("fake-jwt-token-12345"));
    }
}