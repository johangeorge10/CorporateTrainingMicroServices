package com.capstone.enrollment.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class EnrollmentResponseDTO {

    private UUID id;
    private UUID userId;
    private UUID courseId;
	private UUID courseName;
    private LocalDateTime enrolledAt;
    private String status;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getCourseName() {
		return courseName;
	}

	public void setCourseName(UUID courseName) {
		this.courseName = courseName;
	}

	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	public UUID getCourseId() {
		return courseId;
	}
	public void setCourseId(UUID courseId) {
		this.courseId = courseId;
	}
	public LocalDateTime getEnrolledAt() {
		return enrolledAt;
	}
	public void setEnrolledAt(LocalDateTime enrolledAt) {
		this.enrolledAt = enrolledAt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

    // getters & setters
    
}
