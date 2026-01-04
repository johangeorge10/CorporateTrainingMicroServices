package com.capstone.coursemanagement.dto;



import java.util.UUID;

public class CourseResponseDTO {

    private UUID id;
    private String title;
    private String description;
    private String skillLevel;
    private Integer durationHours;
    private Boolean active;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}
	public Integer getDurationHours() {
		return durationHours;
	}
	public void setDurationHours(Integer durationHours) {
		this.durationHours = durationHours;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

    // getters & setters
    
    
}
