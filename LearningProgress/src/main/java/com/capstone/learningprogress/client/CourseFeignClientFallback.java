package com.capstone.learningprogress.client;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.capstone.learningprogress.dto.CourseModuleDTO;

@Component
public class CourseFeignClientFallback implements CourseFeignClient{

	@Override
	public List<CourseModuleDTO> getModulesByCourse(UUID courseId) {
		// TODO Auto-generated method stub
		return (List<CourseModuleDTO>) new CourseModuleDTO
				("course-management-service is currently unavailable");
	}

}
