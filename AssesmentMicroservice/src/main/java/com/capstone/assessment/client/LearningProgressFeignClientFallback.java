package com.capstone.assessment.client;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.capstone.assessment.dto.CourseProgressResponseDTO;

@Component
public class LearningProgressFeignClientFallback implements LearningProgressFeignClient{

	@Override
	public CourseProgressResponseDTO getCourseProgress(UUID courseId, UUID userId) {
		// TODO Auto-generated method stub
		return new CourseProgressResponseDTO(
				0,"Learning-Progress-service is currectly down (fall back method called)"
				);
	}

}
