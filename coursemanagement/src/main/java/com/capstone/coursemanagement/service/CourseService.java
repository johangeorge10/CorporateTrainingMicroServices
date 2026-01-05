package com.capstone.coursemanagement.service;

import com.capstone.coursemanagement.dto.CourseRequestDTO;
import com.capstone.coursemanagement.dto.CourseResponseDTO;
import com.capstone.coursemanagement.entity.Course;
import com.capstone.coursemanagement.repository.CourseModuleRepository;
import com.capstone.coursemanagement.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository repository;
    private final CourseModuleRepository crp;
    public CourseService(CourseRepository repository,CourseModuleRepository crp) {
        this.repository = repository;
        this.crp=crp;
    }

    public CourseResponseDTO createCourse(CourseRequestDTO dto) {
        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setSkillLevel(dto.getSkillLevel());
        course.setDurationHours(dto.getDurationHours());

        return mapToResponse(repository.save(course));
    }

    public CourseResponseDTO getCourseById(UUID id) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return mapToResponse(course);
    }

    public List<CourseResponseDTO> getAllCourses() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public CourseResponseDTO updateCourse(UUID id, CourseRequestDTO dto) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setSkillLevel(dto.getSkillLevel());
        course.setDurationHours(dto.getDurationHours());

        return mapToResponse(repository.save(course));
    }

    public void deleteCourse(UUID id) {
        repository.deleteById(id);
        System.out.println(crp.removeByCourseId(id));
    }

    private CourseResponseDTO mapToResponse(Course course) {
        CourseResponseDTO dto = new CourseResponseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setSkillLevel(course.getSkillLevel());
        dto.setDurationHours(course.getDurationHours());
        dto.setActive(course.getActive());
        return dto;
    }
}
