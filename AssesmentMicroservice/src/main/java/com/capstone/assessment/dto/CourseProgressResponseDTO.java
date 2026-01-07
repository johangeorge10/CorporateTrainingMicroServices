package com.capstone.assessment.dto;


public class CourseProgressResponseDTO {

    private int totalModules;
    private int completedModules;
    private double completionPercentage;
    private boolean eligibleForAssessment;
    
    //For fall back method
    private int progress;
    private String message;
    public CourseProgressResponseDTO(int progress,String message ) 
    {
    	this.progress=progress;
    	this.message=message;
    } 
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
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
	@Override
	public String toString() {
		return "CourseProgressResponseDTO [totalModules=" + totalModules + ", completedModules=" + completedModules
				+ ", completionPercentage=" + completionPercentage + ", eligibleForAssessment=" + eligibleForAssessment
				+ "]";
	}
	

    // getters & setters
    
}
