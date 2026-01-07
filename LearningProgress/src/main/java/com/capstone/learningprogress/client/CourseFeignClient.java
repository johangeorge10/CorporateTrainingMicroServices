package com.capstone.learningprogress.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capstone.learningprogress.dto.CourseModuleDTO;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "course-management-service",fallback=CourseFeignClientFallback.class)
public interface CourseFeignClient {

    @GetMapping("/api/modules/course/{courseId}")
    List<CourseModuleDTO> getModulesByCourse(@PathVariable UUID courseId);
}
