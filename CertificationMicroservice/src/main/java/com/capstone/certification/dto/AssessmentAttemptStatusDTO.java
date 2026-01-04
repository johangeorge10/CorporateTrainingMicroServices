package com.capstone.certification.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class AssessmentAttemptStatusDTO {

    private boolean attempted;
    private boolean passed;
    private Integer scorePercentage;
    private LocalDateTime attemptedAt;
    private UUID assessmentId;
    
    

    public UUID getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(UUID assessmentId) {
		this.assessmentId = assessmentId;
	}

	public boolean isAttempted() {
        return attempted;
    }

    public void setAttempted(boolean attempted) {
        this.attempted = attempted;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public Integer getScorePercentage() {
        return scorePercentage;
    }

    public void setScorePercentage(Integer scorePercentage) {
        this.scorePercentage = scorePercentage;
    }

    public LocalDateTime getAttemptedAt() {
        return attemptedAt;
    }

    public void setAttemptedAt(LocalDateTime attemptedAt) {
        this.attemptedAt = attemptedAt;
    }
}
