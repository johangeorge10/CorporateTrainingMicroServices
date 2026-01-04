package com.capstone.assessment.dto;

public class CreateAssessmentDTO {
    private Integer passPercentage; // optional (default 70)

	public Integer getPassPercentage() {
		return passPercentage;
	}

	public void setPassPercentage(Integer passPercentage) {
		this.passPercentage = passPercentage;
	}
    
}
