package com.capstone.enrollment.controller;

import com.capstone.enrollment.dto.EnrollmentRequestDTO;
import com.capstone.enrollment.dto.EnrollmentResponseDTO;
import com.capstone.enrollment.service.EnrollmentService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }
    @PreAuthorize("hasAnyRole('TRAINEE')")
    @PostMapping
    public EnrollmentResponseDTO enroll(@RequestBody EnrollmentRequestDTO dto) {
        return service.enroll(dto);
    }
    @PreAuthorize("hasAnyRole('TRAINEE')")
    @GetMapping("/user/{userId}")
    public List<EnrollmentResponseDTO> getByUser(@PathVariable UUID userId) {
        return service.getByUser(userId);
    }
    
    //need to map trainer to course and get 
    
    @PreAuthorize("hasAnyRole('TRAINEE')")
    @GetMapping("/course/{courseId}")
    public List<EnrollmentResponseDTO> getByCourse(@PathVariable UUID courseId) {
        return service.getByCourse(courseId);
    }
    @PreAuthorize("hasAnyRole('TRAINEE')")
    @PutMapping("/{enrollmentId}/complete")
    public EnrollmentResponseDTO complete(@PathVariable UUID enrollmentId) {
        return service.markCompleted(enrollmentId);
    }
    @PreAuthorize("hasAnyRole('TRAINEE')")
    @PutMapping("/complete")
    public EnrollmentResponseDTO completeByUserAndCourse(
            @RequestParam UUID userId,
            @RequestParam UUID courseId
    ) {
        return service.markCompletedByUserAndCourse(userId, courseId);
    }

}
