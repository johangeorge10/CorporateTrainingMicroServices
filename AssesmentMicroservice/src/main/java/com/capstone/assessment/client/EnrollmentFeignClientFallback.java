package com.capstone.assessment.client;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class EnrollmentFeignClientFallback implements EnrollmentFeignClient {

    @Override
    public void markCourseCompleted(UUID userId, UUID courseId) {
        System.err.println("⚠ Enrollment Service unavailable. Course completion not updated.");
    }
}

