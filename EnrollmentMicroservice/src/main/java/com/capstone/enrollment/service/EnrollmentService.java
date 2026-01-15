package com.capstone.enrollment.service;

import com.capstone.enrollment.client.CourseFeignClient;
import com.capstone.enrollment.dto.EnrollmentRequestDTO;
import com.capstone.enrollment.dto.EnrollmentResponseDTO;
import com.capstone.enrollment.entity.Enrollment;
import com.capstone.enrollment.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private final EnrollmentRepository repository;
    private final CourseFeignClient courseClient;

    public EnrollmentService(EnrollmentRepository repository,
                             CourseFeignClient courseClient) {
        this.repository = repository;
        this.courseClient = courseClient;
    }

    public EnrollmentResponseDTO enroll(EnrollmentRequestDTO dto) {

        // 1️⃣ Prevent duplicate enrollment
        repository.findByUserIdAndCourseId(dto.getUserId(), dto.getCourseId())
                .ifPresent(e -> {
                    throw new RuntimeException("User already enrolled in this course");
                });

        // 2️⃣ Validate course existence via Course service
        CourseFeignClient.CourseResponse course =
                courseClient.checkCourseExists(dto.getCourseId());

        if (course == null || Boolean.FALSE.equals(course.active)) {
            throw new RuntimeException("Course not found or inactive");
        }

        // 3️⃣ Create enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setUserId(dto.getUserId());
        enrollment.setCourseId(dto.getCourseId());
        enrollment.setEnrolledAt(LocalDateTime.now());
        enrollment.setStatus("ENROLLED");

        return mapToResponse(repository.save(enrollment));
    }

    public List<EnrollmentResponseDTO> getByUser(UUID userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<EnrollmentResponseDTO> getByCourse(UUID courseId) {
        return repository.findByCourseId(courseId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public EnrollmentResponseDTO markCompleted(UUID enrollmentId) {
        Enrollment enrollment = repository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollment.setStatus("COMPLETED");
        return mapToResponse(repository.save(enrollment));
    }
    public EnrollmentResponseDTO markCompletedByUserAndCourse(UUID userId, UUID courseId) {

        Enrollment enrollment = repository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new RuntimeException(
                        "Enrollment not found for userId=" + userId + " courseId=" + courseId));

        enrollment.setStatus("COMPLETED");
        return mapToResponse(repository.save(enrollment));
    }

    private EnrollmentResponseDTO mapToResponse(Enrollment enrollment) {
        EnrollmentResponseDTO dto = new EnrollmentResponseDTO();
        dto.setId(enrollment.getId());
        dto.setUserId(enrollment.getUserId());
        dto.setCourseId(enrollment.getCourseId());
        dto.setEnrolledAt(enrollment.getEnrolledAt());
        dto.setStatus(enrollment.getStatus());
        return dto;
    }
}
