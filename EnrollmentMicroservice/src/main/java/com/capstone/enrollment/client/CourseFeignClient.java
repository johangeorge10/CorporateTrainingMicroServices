package com.capstone.enrollment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capstone.enrollment.config.FeignClientConfig;

import java.util.UUID;
//import com.capstone.enrollment.config.*;
@FeignClient(name = "course-management-service", url = "http://localhost:8082" ,configuration = FeignClientConfig.class)
public interface CourseFeignClient {

    @GetMapping("/api/courses/{courseId}")
    CourseResponse checkCourseExists(@PathVariable UUID courseId);

    class CourseResponse {
        public UUID id;
        public Boolean active;
    }
}
