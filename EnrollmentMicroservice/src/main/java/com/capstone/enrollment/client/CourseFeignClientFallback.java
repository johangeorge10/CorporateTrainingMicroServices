package com.capstone.enrollment.client;

import java.util.UUID;

import org.springframework.stereotype.Component;


@Component
public class CourseFeignClientFallback implements CourseFeignClient{

	@Override
	public CourseResponse checkCourseExists(UUID courseId) {
		// TODO Auto-generated method stub
		return new CourseResponse("course-management-service is currently unavalable");
	}

	
	
}
