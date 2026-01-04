package com.capstone.learningprogress.dto;

import java.util.UUID;

public class ModuleProgressRequestDTO {

    private UUID userId;
    private UUID courseId;
    private UUID moduleId;
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
	public UUID getModuleId() {
		return moduleId;
	}
	public void setModuleId(UUID moduleId) {
		this.moduleId = moduleId;
	}

    // getters & setters
    
}
