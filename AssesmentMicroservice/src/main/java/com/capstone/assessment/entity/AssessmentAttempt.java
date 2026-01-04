package com.capstone.assessment.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
    name = "assessment_attempts",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "assessmentId"})
    }
)
public class AssessmentAttempt {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private UUID assessmentId;

    private Integer scorePercentage;

    private Boolean passed;

    private LocalDateTime attemptedAt;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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

	public Integer getScorePercentage() {
		return scorePercentage;
	}

	public void setScorePercentage(Integer scorePercentage) {
		this.scorePercentage = scorePercentage;
	}

	public Boolean getPassed() {
		return passed;
	}

	public void setPassed(Boolean passed) {
		this.passed = passed;
	}

	public LocalDateTime getAttemptedAt() {
		return attemptedAt;
	}

	public void setAttemptedAt(LocalDateTime attemptedAt) {
		this.attemptedAt = attemptedAt;
	}

    // getters & setters
    
}
