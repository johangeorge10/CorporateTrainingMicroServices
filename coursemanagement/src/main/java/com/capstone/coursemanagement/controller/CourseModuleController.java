package com.capstone.coursemanagement.controller;

import com.capstone.coursemanagement.dto.CourseModuleRequestDTO;
import com.capstone.coursemanagement.dto.CourseModuleResponseDTO;
import com.capstone.coursemanagement.service.CourseModuleService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/modules")
public class CourseModuleController {

    private final CourseModuleService service;

    public CourseModuleController(CourseModuleService service) {
        this.service = service;
    }
    @PreAuthorize("hasRole('TRAINER')")
    @PostMapping
    public CourseModuleResponseDTO createModule(@RequestBody CourseModuleRequestDTO dto) {
        return service.createModule(dto);
    }
    @PreAuthorize("hasRole('TRAINEE')")
    @GetMapping("/course/{courseId}")
    public List<CourseModuleResponseDTO> getModulesByCourse(@PathVariable UUID courseId) {
        return service.getModulesByCourse(courseId);
    }
    @PreAuthorize("hasRole('TRAINER')")
    @GetMapping("/{moduleId}")
    public CourseModuleResponseDTO getModule(@PathVariable UUID moduleId) {
        return service.getModuleById(moduleId);
    }
    @PreAuthorize("hasRole('TRAINER')")
    @PutMapping("/{moduleId}")
    public CourseModuleResponseDTO updateModule(
            @PathVariable UUID moduleId,
            @RequestBody CourseModuleRequestDTO dto) {
        return service.updateModule(moduleId, dto);
    }
    @PreAuthorize("hasRole('TRAINER')")
    @DeleteMapping("/{moduleId}")
    public String deleteModule(@PathVariable UUID moduleId) {
        service.deleteModule(moduleId);
        return "Module deleted successfully";
    }
}
