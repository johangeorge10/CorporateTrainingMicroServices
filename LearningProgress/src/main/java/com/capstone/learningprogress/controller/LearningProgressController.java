package com.capstone.learningprogress.controller;

import com.capstone.learningprogress.dto.CourseProgressResponseDTO;
import com.capstone.learningprogress.dto.ModuleProgressRequestDTO;
import com.capstone.learningprogress.service.LearningProgressService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/progress")
public class LearningProgressController {

    private final LearningProgressService service;

    public LearningProgressController(LearningProgressService service) {
        this.service = service;
    }
    @PreAuthorize("hasAnyRole('TRAINEE')")
    @PostMapping("/module/complete")
    public String completeModule(@RequestBody ModuleProgressRequestDTO dto) {
        service.markModuleCompleted(dto);
        return "Module marked as completed";
    }
    @PreAuthorize("hasAnyRole('TRAINEE')")
    @GetMapping("/course/{courseId}/user/{userId}")
    public CourseProgressResponseDTO getCourseProgress(
            @PathVariable UUID courseId,
            @PathVariable UUID userId) {
        return service.getCourseProgress(userId, courseId);
    }
}
