package com.capstone.certification.client;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.capstone.certification.dto.AssessmentAttemptStatusDTO;

@Component
public class AssessmentClientFallback implements AssessmentClient{

	@Override
	public AssessmentAttemptStatusDTO getAttemptStatus(UUID userId, UUID courseId) {
		// TODO Auto-generated method stub
		return new AssessmentAttemptStatusDTO(
				0,"Assessment service unavailable. Circuit breaker fallback executed.");
	}

	

}
