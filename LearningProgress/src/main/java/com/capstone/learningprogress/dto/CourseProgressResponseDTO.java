package com.capstone.learningprogress.dto;

import java.util.List;
import java.util.UUID;

public class CourseProgressResponseDTO {

    private int totalModules;
    private int completedModules;
    private double completionPercentage;
    public List<UUID> getCompletedModuleIds() {
		return completedModuleIds;
	}
	public void setCompletedModuleIds(List<UUID> completedModuleIds) {
		this.completedModuleIds = completedModuleIds;
	}
	private boolean eligibleForAssessment;
    private List<UUID> completedModuleIds;
	public int getTotalModules() {
		return totalModules;
	}
	public void setTotalModules(int totalModules) {
		this.totalModules = totalModules;
	}
	public int getCompletedModules() {
		return completedModules;
	}
	public void setCompletedModules(int completedModules) {
		this.completedModules = completedModules;
	}
	public double getCompletionPercentage() {
		return completionPercentage;
	}
	public void setCompletionPercentage(double completionPercentage) {
		this.completionPercentage = completionPercentage;
	}
	public boolean isEligibleForAssessment() {
		return eligibleForAssessment;
	}
	public void setEligibleForAssessment(boolean eligibleForAssessment) {
		this.eligibleForAssessment = eligibleForAssessment;
	}

    // getters & setters
    
}
