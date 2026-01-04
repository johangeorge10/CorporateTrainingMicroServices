package com.capstone.assessment.dto;

import java.util.Map;
import java.util.UUID;

public class AssessmentAttemptRequestDTO {

    private UUID userId;
    private UUID assessmentId;

    // questionId → selectedOption
    private Map<UUID, String> answers;

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public UUID getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(UUID assessmentId) {
		this.assessmentId = assessmentId;
	}

	public Map<UUID, String> getAnswers() {
		return answers;
	}

	public void setAnswers(Map<UUID, String> answers) {
		this.answers = answers;
	}

    // getters & setters
    
}
