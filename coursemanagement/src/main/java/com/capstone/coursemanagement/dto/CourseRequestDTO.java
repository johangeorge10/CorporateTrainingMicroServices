package com.capstone.coursemanagement.dto;

public class CourseRequestDTO {

    private String title;
    private String description;
    private String skillLevel;
    private Integer durationHours;
	public CourseRequestDTO(String title, String description, String skillLevel, Integer durationHours) {
		super();
		this.title = title;
		this.description = description;
		this.skillLevel = skillLevel;
		this.durationHours = durationHours;
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

    // getters & setters
	
    
}

