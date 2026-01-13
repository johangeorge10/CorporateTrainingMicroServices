package com.capstone.enrollment.client;

import com.capstone.enrollment.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "course-management-service", configuration = FeignClientConfig.class)
public interface CourseFeignClient {

    @GetMapping("/api/courses/{courseId}")
    CourseResponse checkCourseExists(@PathVariable UUID courseId);

    class CourseResponse {
        public UUID id;
        public Boolean active;
    }
}
