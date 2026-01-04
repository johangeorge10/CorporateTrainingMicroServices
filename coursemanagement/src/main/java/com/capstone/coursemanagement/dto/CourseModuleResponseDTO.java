package com.capstone.coursemanagement.dto;

import java.util.UUID;

public class CourseModuleResponseDTO {

    private UUID id;
    private UUID courseId;
    private Integer moduleOrder;
    private String title;
    private String videoUrl;
    private Integer durationMinutes;
    private Boolean active;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getCourseId() {
		return courseId;
	}
	public void setCourseId(UUID courseId) {
		this.courseId = courseId;
	}
	public Integer getModuleOrder() {
		return moduleOrder;
	}
	public void setModuleOrder(Integer moduleOrder) {
		this.moduleOrder = moduleOrder;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public Integer getDurationMinutes() {
		return durationMinutes;
	}
	public void setDurationMinutes(Integer durationMinutes) {
		this.durationMinutes = durationMinutes;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

    
}
