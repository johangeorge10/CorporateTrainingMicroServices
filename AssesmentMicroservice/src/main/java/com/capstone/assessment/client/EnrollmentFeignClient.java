package com.capstone.assessment.client;

import java.util.UUID;

import com.capstone.assessment.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
	    name = "enrollment-service",
	    configuration = FeignClientConfig.class,
	    fallback = EnrollmentFeignClientFallback.class
	)
	public interface EnrollmentFeignClient {

	    @PutMapping("/api/enrollments/complete")
	    void markCourseCompleted(
	        @RequestParam("userId") UUID userId,
	        @RequestParam("courseId") UUID courseId
	    );
	}
