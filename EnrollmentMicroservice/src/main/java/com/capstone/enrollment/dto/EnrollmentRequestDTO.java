package com.capstone.enrollment.dto;

import java.util.UUID;

public class EnrollmentRequestDTO {

    private UUID userId;
    private UUID courseId;
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

    // getters & setters
    
}
