package com.capstone.coursemanagement.controller;


import com.capstone.coursemanagement.dto.CourseRequestDTO;
import com.capstone.coursemanagement.dto.CourseResponseDTO;
import com.capstone.coursemanagement.service.CourseService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    //Controller
    @PreAuthorize("hasRole('TRAINER')")
    @PostMapping
    public CourseResponseDTO createCourse(@RequestBody CourseRequestDTO dto) {
        return service.createCourse(dto);
    }
    @PreAuthorize("hasRole('TRAINEE')")
    @GetMapping("/{id}")
    public CourseResponseDTO getCourse(@PathVariable UUID id) {
        return service.getCourseById(id);
    }
    @PreAuthorize("hasAnyRole('TRAINER','ADMIN','TRAINEE')")
    @GetMapping
    public List<CourseResponseDTO> getAllCourses() {
        return service.getAllCourses();
    }
    
    @PreAuthorize("hasRole('TRAINER')")
    @PutMapping("/{id}")
    public CourseResponseDTO updateCourse(
            @PathVariable UUID id,
            @RequestBody CourseRequestDTO dto) {
        return service.updateCourse(id, dto);
    }
    @PreAuthorize("hasRole('TRAINER','ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable UUID id) {
        service.deleteCourse(id);
        return "Course deleted successfully";
    }
}
